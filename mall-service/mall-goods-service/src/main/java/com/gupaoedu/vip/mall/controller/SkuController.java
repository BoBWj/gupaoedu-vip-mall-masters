package com.gupaoedu.vip.mall.controller;


import com.gupao.edu.vip.com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.mall.goods.model.Sku;
import com.gupaoedu.vip.mall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sku ")
public class SkuController {

    @Autowired
    private SkuService service;

    /**
     * 根据推广分类查询推广分类
     */
    @GetMapping(value = "/aditems/type")
    public RespResult<List<Sku>> typeItems(@RequestParam(value = "id") Integer id){


        //查询
        List<Sku> skus=service.typeSkuItem(id);

        return RespResult.ok(skus);

    }
}
