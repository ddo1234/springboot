package com.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.QueryPageParam;
import com.common.Result;
import com.pojo.Storage2;
import com.pojo.User;
import com.service.Storage2Service;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author leo
 * @since 2022-11-27
 */
@RestController
@RequestMapping("/storage2")
public class Storage2Controller {


    @GetMapping("/getUser")
    public List getUser() {
        return storage2Service.findUsers();
    }

    @Autowired
    private Storage2Service storage2Service;

    //前端loadGet方法
    @GetMapping("/list")
    public List<Storage2> list() {
        System.out.println(storage2Service.list());
        return storage2Service.list();
    }

    //新增1
    @PostMapping("/save")
    public Result save(@RequestBody Storage2 storage2) {
        //直接调用封装好方法save
        return storage2Service.save(storage2) ? Result.successful() : Result.fail();
    }

    //修改1
    @PostMapping("/update")
    public Result Update(@RequestBody Storage2 storage2) {
        //直接调用封装好方法根据id更新
        return storage2Service.updateById(storage2) ? Result.successful() : Result.fail();
    }
//    //修改
//    @PostMapping("/mod")
//    public boolean mod(@RequestBody Storage2 storage2) {
//        return storage2Service.updateById(storage2);
//    }
//    //新增或更改
//    @PostMapping("/saveOrMod")
//    public boolean saveOrMod(@RequestBody Storage2 storage2) {
//        return storage2Service.saveOrUpdate(storage2);
//    }

    //删除返回Result1
    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        //直接调用封装好方法根据id删除
        return storage2Service.removeById(id) ? Result.successful() : Result.fail();
    }
    //分页查询(方法1：利用Map,优势不用自己封装，不好在于要对数据进行强转)
//    @PostMapping("/listPage")
//    public List<Storage2> listPage(@RequestBody HashMap map) {
//        System.out.println("size==" + map.get("pageSize"));
//        System.out.println("num==" + map.get("pageNum"));
//        System.out.println(map);
//        return null;
//    }

    //数据封装1 对应前端loadPost方法
    //查询（模糊、匹配），带参数查询
    @PostMapping("/listPageC1")
    //分页查询(方法2：自定义封装+map，需要自己封装，但是数据类型统一，更加清晰，省去了数据转化跟判断)
    public Result listPageC1(@RequestBody QueryPageParam queryPageParam) {
        //将前端的数据存在param
        HashMap param = queryPageParam.getParam();
        //前端的三个查询
        String name = (String) param.get("name");
        String sex = (String) param.get("sex");
        String person = (String) param.get("person");
        Page<Storage2> page = new Page<>();
        //假如前端有传入的PageNum和PageSize,这里进行修改,不使用默认的值
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());
        //&& !"null".equals(name)
        LambdaQueryWrapper<Storage2> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name)) {
            lambdaQueryWrapper.like(Storage2::getName, name);
        }
        if (StringUtils.isNotBlank(sex)) {
            lambdaQueryWrapper.eq(Storage2::getSex, sex);
        }
        if (StringUtils.isNotBlank(person)) {
            lambdaQueryWrapper.like(Storage2::getPerson, person);
        }
        //使用 Wrapper 自定义SQL
        IPage result = storage2Service.pageQ(page, lambdaQueryWrapper);

        System.out.println("total:" + result.getTotal());
        //IPage
        return Result.successful(result.getRecords(), result.getTotal());
    }
}
