package com.gupaoedu.vip.mall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.goods.model.ADItems;
import com.gupaoedu.mall.goods.model.Sku;
import com.gupaoedu.vip.mall.mapper.AdItemsMapper;
import com.gupaoedu.vip.mall.mapper.SkuMapper;
import com.gupaoedu.vip.mall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService  {

    @Autowired
    private AdItemsMapper adItemsMapper;

    @Autowired
    private SkuMapper skuMapper;


    /**
     * 根据推广产品分类id查下指定分类下的产品列表
     * @param id
     * @return
     */
    @Override
    public List<Sku> typeSkuItem(Integer id) {

        //1.查询当前分类下的所有的列表信息
        QueryWrapper<ADItems> adItemsQueryWrapper=new QueryWrapper<ADItems>();
        adItemsQueryWrapper.eq("type",id);
        List<ADItems> adItems=adItemsMapper.selectList(adItemsQueryWrapper);



        //2.g根据推广列表查询产品列表信息
        List<String> skuids   =adItems.stream().map(adItem -> adItem.getSkuId()).collect(Collectors.toList());


        return skuMapper.selectBatchIds(skuids);
    }
}
