package com.gupaoedu.vip.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.goods.model.*;
import com.gupaoedu.vip.mall.mapper.BrandMapper;
import com.gupaoedu.vip.mall.mapper.CategpryMapper;
import com.gupaoedu.vip.mall.mapper.SkuMapper;
import com.gupaoedu.vip.mall.mapper.SpuMapper;
import com.gupaoedu.vip.mall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;


@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private CategpryMapper categpryMapper;

    @Autowired
    private BrandMapper brandMapper;



    /***
     *  产品保存
     */
    @Override
    public void saveProduct(Product product) {
        //Spu
        Spu spu = product.getSpu();

        if (StringUtils.isEmpty(spu.getId())) {
            //上架
            spu.setIsMarketable(1);
            //未删除
            spu.setIsDelete(0);
            //状态
            spu.setStatus(1);
            //添加
            spuMapper.insert(spu);
        }else {
            spuMapper.updateById(spu);
            //删除sku集合
            skuMapper.delete(new QueryWrapper<Sku>().eq("spq_id",spu.getId()));
        }
        //查询三级分类
        Category category = categpryMapper.selectById(spu.getCategoryThreeId());
        //查询品牌
        Brank brand = brandMapper.selectById(spu.getBrandId());
        //当前时间
        Date now = new Date();
        //新增Sku集合
        for (Sku sku : product.getSkus()) {
            //设置名字
            String skuName = spu.getName();
            Map<String, String> attrMap = JSON.parseObject(sku.getSkuAttribute(),
                    Map.class);
            for (Map.Entry<String, String> entry : attrMap.entrySet()) {
                skuName += " " + entry.getValue();
            }
            sku.setName(skuName);
            //设置图片
            sku.setImages(spu.getImages());
            //设置状态
            sku.setStatus(1);
            //设置类目ID
            sku.setCategoryId(spu.getCategoryThreeId());
            //设置类目名称
            sku.setCategoryName(category.getName());
            //设置品牌ID
            sku.setBrandId(brand.getId());
            //设置品牌名称
            sku.setBrandName(brand.getName());
            //设置Spuid
            sku.setSpuId(spu.getId());
            //时间
            sku.setCreateTime(now);
            sku.setUpdateTime(now);
            //增加
            skuMapper.insert(sku);
        }
    }
}
