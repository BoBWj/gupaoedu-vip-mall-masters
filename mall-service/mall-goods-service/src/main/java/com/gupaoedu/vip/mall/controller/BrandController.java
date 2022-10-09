package com.gupaoedu.vip.mall.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gupao.edu.vip.com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.mall.goods.model.Brand;
import com.gupaoedu.mall.goods.model.Category;
import com.gupaoedu.vip.mall.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
@CrossOrigin //跨域
public class BrandController {

    @Autowired
    private BrandService brandService;

    /*8
    增加方法
     */
    @PostMapping ()
    public RespResult add(@RequestBody Brand brand){

        return RespResult.ok();
    }

    /**
     * 修改方法
     */
    @PutMapping
    public RespResult update(@RequestBody Brand brand){

        brandService.updateById(brand);

        return RespResult.ok();


    }

    /**
     * 删除方法
     */
    @DeleteMapping("/{id}")
    public RespResult delete(@PathVariable(value = "id") String id ){

        brandService.removeById(id);
        return RespResult.ok();
    }

    /****
     * 条件查询
     */

    @PostMapping(value = "/list")
    public RespResult<List<Brand>> listRespResult(@RequestBody(required = false) Brand brand){

        List<Brand> list=brandService.queryList(brand);

        return RespResult.ok(list);

    }

    /****
     * 条件分页查询
     */

    @PostMapping(value = "list/{page}/{size}")
    public RespResult<Page<Brand>> pageInfo(@PathVariable(value = "page")Long currentPage,
                                            @PathVariable(value = "size")Long size,
                                            @RequestBody(required = false) Brand brand){

        Page<Brand> pageInfo=brandService.queryPageList(currentPage, size, brand);

        return RespResult.ok(pageInfo);


    }

    /**
     * 根据分类id查询品牌集合
     */
    @GetMapping("/category/{pid}")
    public RespResult<List<Category>> categoryBrands (@PathVariable(value = "pid") Integer pid){

        List<Brand> brands =brandService.queryBrankByCategoryId(pid);
        return RespResult.ok(brands);

    }
}
