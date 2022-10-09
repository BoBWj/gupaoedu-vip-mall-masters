package com.gupaoedu.vip.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupaoedu.mall.goods.model.Sku;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface SkuService  extends IService<Sku> {

    /**
     * 根据推广产品分类id查下指定分类下的产品列表
     * @param id
     * @return
     */
    List<Sku> typeSkuItem(Integer id);
}
