package com.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.QueryPageParam;
import com.common.Result;
import com.pojo.Goodstype;
import com.service.GoodstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/goodstype")
public class GoodstypeController {

    @Autowired
    private GoodstypeService goodstypeService;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Goodstype goodstype) {
        return goodstypeService.save(goodstype) ? Result.successful() : Result.fail();
    }

    //更新
    @PostMapping("/update")
    public Result Update(@RequestBody Goodstype goodstype) {
        return goodstypeService.updateById(goodstype) ? Result.successful() : Result.fail();
    }

    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return goodstypeService.removeById(id) ? Result.successful() : Result.fail();
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam queryPageParam) {

        HashMap param = queryPageParam.getParam();
        String name = (String) param.get("name");
        Page<Goodstype> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        LambdaQueryWrapper<Goodstype> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Goodstype::getName, name);
        }
        IPage result = goodstypeService.pageCC(page, lambdaQueryWrapper);
        return Result.successful(result.getRecords(), result.getTotal());


    }

}
