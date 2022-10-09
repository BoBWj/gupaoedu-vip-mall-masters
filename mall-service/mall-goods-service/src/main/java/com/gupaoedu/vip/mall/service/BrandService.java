package com.gupaoedu.vip.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.goods.model.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService extends IService<Brand> {

    /**
     *  多条件查下
     */

    List<Brand> queryList(Brand brand);

    /**
     * 分页查询
     */
    Page<Brand> queryPageList(Long currentPage, Long size, Brand brand);

    /**
     * 根据分类ID查询相对应的品牌jige
     */
    List<Brand> queryBrankByCategoryId(Integer id);
}
