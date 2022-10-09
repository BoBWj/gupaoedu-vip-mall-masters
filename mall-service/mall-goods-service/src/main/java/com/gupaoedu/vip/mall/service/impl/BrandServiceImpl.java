package com.gupaoedu.vip.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.mall.goods.model.Brand;
import com.gupaoedu.vip.mall.mapper.BrandMapper;
import com.gupaoedu.vip.mall.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    private  BrandMapper brandMapper;

    /****
     * 多条件查询S
     * @param brand
     * @return
     */
    @Override
    public List<Brand> queryList(Brand brand) {

        QueryWrapper<Brand> queryWrapper =new QueryWrapper<Brand>();
        if (!StringUtils.isEmpty(brand.getName())){
            queryWrapper.like("name",brand.getName());
        }
//        if (!StringUtils.isEmpty(brand.getName())){
//
//        }

        if (!StringUtils.isEmpty(brand.getInitial())){
            queryWrapper.like("initial",brand.getInitial());
        }
        return brandMapper.selectList(queryWrapper);
    }

    /***
     * 分页查询
     * @param brand
     * @return
     */

    @Override
    public Page<Brand> queryPageList(Long currentPage, Long size, Brand brand) {

        Page<Brand> page = brandMapper.selectPage(
                new Page<Brand>(currentPage, size),
                new QueryWrapper<Brand>()
                        .like("name", brand.getName()));
        return page;
    }

    /**
     * 根据分类ID查询品牌集合
     * @param id(分类id)
     * @return
     */
    @Override
    public List<Brand> queryBrankByCategoryId(Integer id) {
        //根据分类ID查询品牌ID集合
        List<Integer> brandIds=brandMapper.queryBrandIds(id);
        //根据品牌ID查询品牌集合
        if (brandIds !=null &&brandIds.size()>0){
            return brandMapper.selectList(new QueryWrapper<Brand>().in("id",brandIds));
        }
        //
        return null;
    }
}
