package com.gupaoedu.vip.mall.controller;


import com.gupao.edu.vip.com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.mall.goods.model.Product;
import com.gupaoedu.vip.mall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin //跨域
public class SpuController {


    @Autowired
    private SpuService spuService;
    @PostMapping("/save")
    public RespResult save(@RequestBody Product product){

        spuService.saveProduct(product);

        return RespResult.ok();


    }
}
