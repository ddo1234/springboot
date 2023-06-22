package com.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.QueryPageParam;
import com.common.Result;
import com.pojo.Goods;
import com.pojo.Record;
import com.pojo.RecordRes;
import com.service.GoodsService;
import com.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private IRecordService iRecordService;

    @Autowired
    private GoodsService goodsService;

    //    模糊查询
    @PostMapping("/select")
    public Result select(@RequestBody QueryPageParam queryPageParam) {

        HashMap param = queryPageParam.getParam();
        String name = (String) param.get("name");
        String goodsType = (String) param.get("goodsType");
        String storage = (String) param.get("storage");
        String roleId = (String) param.get("roleId");
        String userId = (String) param.get("userId");
        String remark = (String) param.get("remark");

        Page<Record> recordPage = new Page();
        recordPage.setCurrent(queryPageParam.getPageNum());
        recordPage.setSize(queryPageParam.getPageSize());

        QueryWrapper<Record> queryWrapper = new QueryWrapper();
        queryWrapper.apply(" a.goods_id=b.id ");

        if ("2".equals(roleId)) {
            // queryWrapper.eq(Record::getUserid,userId);
            queryWrapper.apply(" a.user_id= " + userId);
        }

        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            queryWrapper.like("b.name", name);
        }
        if (StringUtils.isNotBlank(goodsType) && !"null".equals(goodsType)) {
            queryWrapper.eq("b.goodsType", goodsType);
        }
        if (StringUtils.isNotBlank(storage) && !"null".equals(storage)) {
            queryWrapper.eq("b.storage", storage);
        }
        if (StringUtils.isNotBlank(remark) && !"null".equals(remark)) {
            queryWrapper.eq("a.remark", remark);
        }

        IPage result = iRecordService.RPage(recordPage, queryWrapper);
        return Result.successful(result.getRecords(), result.getTotal());
    }

    //    出入库
    @PostMapping("/save")
    public Result save(@RequestBody Record record) {
        Goods goods = goodsService.getById(record.getGoodsId());
        int n = record.getCount();
        int num;

        if ("2".equals(record.getAction())) {
            //出库
            num = goods.getCount() - n;
            record.setCount(-n);
        } else {
            //入库
            num = goods.getCount() + n;
            record.setCount(n);
        }
        goods.setCount(num);
        goodsService.updateById(goods);

        return iRecordService.save(record) ? Result.successful() : Result.fail();
    }

    //修改
    @PutMapping
    public Result update(@RequestBody Record record) {
        Goods goods = goodsService.getById(record.getGoodsId());

        int n = record.getCount() - (iRecordService.getById(record.getId())).getCount();
        int num = goods.getCount() + n;
        ;

        goods.setCount(num);
        goodsService.updateById(goods);

        return iRecordService.updateById(record) ? Result.successful() : Result.fail();
    }

    //    删除
    @DeleteMapping("{id}")
    public Result del(@PathVariable Integer id) {
        return iRecordService.removeById(id) ? Result.successful() : Result.fail();
    }

    @PutMapping("/updateGoods")
    public Result updateGoods(@RequestBody Record record) {
        Goods goods = goodsService.getById(record.getGoodsId());

        int num = goods.getCount() - record.getCount();
        ;

        goods.setCount(num);
        goodsService.updateById(goods);
        return goodsService.updateById(goods) ? Result.successful() : Result.fail();
    }

    @GetMapping("/listStorage2")
    public Result listStorage2() {
        return Result.successful(iRecordService.listStorage());
    }

    @GetMapping("/listGoodsType")
    public Result listGoodsType() {
        return Result.successful(iRecordService.listGoodsType());
    }

}
