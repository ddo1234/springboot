package com.controller;


import com.common.QueryPageParam;
import com.common.Result;
import com.pojo.Goods;
import com.service.GoodsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ash
 * @since 2022-11-24
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    //添加
    @PostMapping("/save")
    public Result save(@RequestBody Goods goods) {
        return goodsService.save(goods) ? Result.successful() : Result.fail();
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Goods goods) {
        return goodsService.updateById(goods) ? Result.successful() : Result.fail();
    }


    @PostMapping("/delAll")
    public Result delAll(@RequestBody List<String> list) {
        return goodsService.removeByIds(list) ? Result.successful() : Result.fail();
    }

    //查找
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        String goodstype = (String) param.get("goodstype");
        String storage = (String) param.get("storage");
        Page<Goods> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Goods::getName, name);
        }
        if (StringUtils.isNotBlank(goodstype) && !"null".equals(goodstype)) {
            lambdaQueryWrapper.eq(Goods::getGoodstype, goodstype);
        }
        if (StringUtils.isNotBlank(storage) && !"null".equals(storage)) {
            lambdaQueryWrapper.eq(Goods::getStorage, storage);
        }
        IPage result = goodsService.page1(page, lambdaQueryWrapper);
        return Result.successful(result.getRecords(), result.getTotal());
    }

    //查询所有类型
    @GetMapping("/listType")
    public List listType() {
        return goodsService.findType();
    }

    //查找所有仓库
    @GetMapping("/listStorage")
    public List listStorage() {
        return goodsService.findStorage();
    }

    @GetMapping("/listTotal")
    public List listTotal() {
        return goodsService.findTotal();
    }

    @GetMapping("/listLess")
    public List<Goods> listLess() {
        return goodsService.findLess();
    }

    @GetMapping("/getCount")
    public Integer getCount() {
        return goodsService.getCount();
    }


}
