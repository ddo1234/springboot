////package com.controller;
////
////
////import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
////import com.baomidou.mybatisplus.core.metadata.IPage;
////import com.baomidou.mybatisplus.core.toolkit.StringUtils;
////import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
////import com.common.QueryPageParam;
////import com.common.Result;
////import com.pojo.Storage;
////import com.service.StorageService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.HashMap;
////import java.util.List;
////
/////**
//// * <p>
//// *  前端控制器
//// * </p>
//// *
//// * @author leo
//// * @since 2022-11-26
//// */
////@RestController
////@RequestMapping("/storage")
////public class StorageController {
////    @Autowired
////    private StorageService storageService;
////
////    @GetMapping("/list")
////    public List<Storage> list() {
////        return storageService.list();
////    }
////
////    //新增
////    @PostMapping("/save")
////    public boolean save(@RequestBody Storage storage) {
////        return storageService.save(storage);
////    }
////
////    //修改
////    @PostMapping("/mod")
////    public boolean mod(@RequestBody Storage storage) {
////        return storageService.updateById(storage);
////    }
////
////    //新增或更改
////    @PostMapping("/saveOrMod")
////    public boolean saveOrMod(@RequestBody Storage storage) {
////        return storageService.saveOrUpdate(storage);
////    }
////
////    //删除
////    @GetMapping("/delete")
////    public boolean delete(Integer id) {
////        return storageService.removeById(id);
////    }
////
////    //查询（模糊、匹配）
////    @PostMapping("/listP")
////    public List<Storage> listP(@RequestBody Storage storage) {
////        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
////        if (StringUtils.isNotBlank(storage.getName()))
////        {
////            lambdaQueryWrapper.like(Storage::getName, storage.getName());
////        }
////        //Storage::getName指的是数据库里面的值，Storage.getName()指用户输入的值，
////        // 与前者相模糊查询 类似 name like %小明%
////        //模糊
////        //lambdaQueryWrapper.like(Storage::getName,storage.getName());
////        //匹配
////        return storageService.list(lambdaQueryWrapper);
////    }
////
////    //分页查询(方法1：利用Map,优势不用自己封装，不好在于要对数据进行强转)
////    @PostMapping("/listPage")
////    public List<Storage> listPage(@RequestBody HashMap map) {
////        System.out.println("size==" + map.get("pageSize"));
////        System.out.println("num==" + map.get("pageNum"));
////        System.out.println(map);
////        return null;
////    }
////
////        //分页查询(方法2：自定义封装+map，需要自己封装，但是数据类型统一，更加清晰)
////    @PostMapping("/listPage2")
////    public List<Storage> listPage2(@RequestBody QueryPageParam queryPageParam) {
//////        HashMap param = new HashMap();
////        HashMap param = queryPageParam.getParam();
////        String name = (String) param.get("name");
////
//////        System.out.println("size=="+queryPageParam.getPageSize());
//////        System.out.println("num=="+queryPageParam.getPageNum());
////        //将查询到的参数，放入一个map里面，利用hashmap对map里面的值单独查询
//////        HashMap hashMap=queryPageParam.getParam();
//////        System.out.println("param=="+queryPageParam.getParam());
//////        System.out.println("name=="+hashMap.get("name"));
//////        System.out.println("no=="+hashMap.get("no"));
////
////        Page<Storage> page = new Page<>();
////        page.setCurrent(queryPageParam.getPageNum());
////        page.setSize(queryPageParam.getPageSize());
////
////        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper();
////        lambdaQueryWrapper.like(Storage::getName, name);
////
////        IPage result = storageService.page(page, lambdaQueryWrapper);
////
////        System.out.println("total:" + result.getTotal());
////
////        return result.getRecords();
////    }
////
////    //自定义分页
////    @PostMapping("/listPageC")
////    public List<Storage> listPageC(@RequestBody QueryPageParam queryPageParam) {
////        //        HashMap param = new HashMap();
////        HashMap param = queryPageParam.getParam();
////        String name = (String) param.get("name");
////        Page<Storage> page = new Page<>();
////        page.setCurrent(queryPageParam.getPageNum());
////        page.setSize(queryPageParam.getPageSize());
////        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper();
////        lambdaQueryWrapper.like(Storage::getName, name);
//////        IPage result=storageService.pageC(page);
////        //使用 Wrapper 自定义SQL
////        IPage result = storageService.pageCC(page, lambdaQueryWrapper);
////        System.out.println("total:" + result.getTotal());
////
////        return result.getRecords();
////    }
////
////    //数据封装
////    @PostMapping("/listPageC1")
////    public Result listPageC1(@RequestBody QueryPageParam queryPageParam) {
////        //        HashMap param = new HashMap();
////        HashMap param = queryPageParam.getParam();
////        String name = (String) param.get("name");
////        Page<Storage> page = new Page<>();
////        page.setCurrent(queryPageParam.getPageNum());
////        page.setSize(queryPageParam.getPageSize());
////
////        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper();
////        lambdaQueryWrapper.like(Storage::getName, name);
////
//////        IPage result=storageService.pageC(page);
////        //使用 Wrapper 自定义SQL
////        IPage result = storageService.pageCC(page, lambdaQueryWrapper);
////
////        System.out.println("total:" + result.getTotal());
////
////        return Result.successful(result.getRecords(), result.getTotal());
////    }
////}
//


//package com.controller;
//
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.common.QueryPageParam;
//import com.common.Result;
//import com.pojo.Storage;
//import com.service.StorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * <p>
// *  前端控制器
// * </p>
// *
// * @author leo
// * @since 2022-11-27
// */
//@RestController
//@RequestMapping("/storage")
//public class StorageController {
//    @Resource
//    private StorageService storageService;
//
//    @GetMapping("/list")
//    public List<Storage> list() {
//        return storageService.list();
//    }
//    @GetMapping("/findById")
//    public Result findById(@RequestBody String id) {
//        List list=storageService.lambdaQuery().eq(Storage::getId,id).list();
//        //判断list的表情况
//        return list.size()>0?Result.successful(list):Result.fail();
//    }
//
//    //新增
//    @PostMapping("/save")
//    public Result save(@RequestBody Storage storage2) {
//        return storageService.save(storage2)?Result.successful():Result.fail();
//    }
//    //更新
//    @PostMapping("/update")
//    public Result Update(@RequestBody Storage storage2){
//        return storageService.updateById(storage2)?Result.successful():Result.fail();
//    }
//    //修改
//    @PostMapping("/mod")
//    public boolean mod(@RequestBody Storage storage2) {
//        return storageService.updateById(storage2);
//    }
//
//    //新增或更改
//    @PostMapping("/saveOrMod")
//    public boolean saveOrMod(@RequestBody Storage storage2) {
//        return storageService.saveOrUpdate(storage2);
//    }
//
//    //删除
//    @GetMapping("/delete")
//    public boolean delete(Integer id) {
//        return storageService.removeById(id);
//    }
//    //删除返回Result
//    @GetMapping("/del")
//    public Result del(@RequestParam String id) {
//        return storageService.removeById(id)?Result.successful():Result.fail();
//    }
//
//    //查询（模糊、匹配）
//    @PostMapping("/listP")
//    public Result listP(@RequestBody Storage storage2) {
//        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        if (StringUtils.isNotBlank(storage2.getName()))
//        {
//            lambdaQueryWrapper.like(Storage::getName, storage2.getName());
//        }
//        //Storage::getName指的是数据库里面的值，Storage.getName()指用户输入的值，
//        // 与前者相模糊查询 类似 name like %小明%
//        //模糊
//        //lambdaQueryWrapper.like(Storage::getName,storage.getName());
//        //匹配
//
//        //将返回数据封装成result
//        return Result.successful(storageService.list(lambdaQueryWrapper));
//    }
//
//    //分页查询(方法1：利用Map,优势不用自己封装，不好在于要对数据进行强转)
//    @PostMapping("/listPage")
//    public List<Storage> listPage(@RequestBody HashMap map) {
//        System.out.println("size==" + map.get("pageSize"));
//        System.out.println("num==" + map.get("pageNum"));
//        System.out.println(map);
//        return null;
//    }
//
//    //分页查询(方法2：自定义封装+map，需要自己封装，但是数据类型统一，更加清晰)
//    @PostMapping("/listPage2")
//    public List<Storage> listPage2(@RequestBody QueryPageParam queryPageParam) {
////        HashMap param = new HashMap();
//        HashMap param = queryPageParam.getParam();
//        String name = (String) param.get("name");
//
////        System.out.println("size=="+queryPageParam.getPageSize());
////        System.out.println("num=="+queryPageParam.getPageNum());
//        //将查询到的参数，放入一个map里面，利用hashmap对map里面的值单独查询
////        HashMap hashMap=queryPageParam.getParam();
////        System.out.println("param=="+queryPageParam.getParam());
////        System.out.println("name=="+hashMap.get("name"));
////        System.out.println("no=="+hashMap.get("no"));
//
//        Page<Storage> page = new Page<>();
//        page.setCurrent(queryPageParam.getPageNum());
//        page.setSize(queryPageParam.getPageSize());
//
//        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper();
//        lambdaQueryWrapper.like(Storage::getName, name);
//
//        IPage result = storageService.page(page, lambdaQueryWrapper);
//
//        System.out.println("total:" + result.getTotal());
//
//        return result.getRecords();
//    }
//
//    //自定义分页
//    @PostMapping("/listPageC")
//    public List<Storage> listPageC(@RequestBody QueryPageParam queryPageParam) {
//        //        HashMap param = new HashMap();
//        HashMap param = queryPageParam.getParam();
//        String name = (String) param.get("name");
//        Page<Storage> page = new Page<>();
//        page.setCurrent(queryPageParam.getPageNum());
//        page.setSize(queryPageParam.getPageSize());
//        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper();
//        lambdaQueryWrapper.like(Storage::getName, name);
////        IPage result=Storage2.pageC(page);
//        //使用 Wrapper 自定义SQL
//        IPage result = storageService.pageCC(page, lambdaQueryWrapper);
//        System.out.println("total:" + result.getTotal());
//
//        return result.getRecords();
//    }
//
//    //数据封装
//    @PostMapping("/listPageC1")
//    public Result listPageC1(@RequestBody QueryPageParam queryPageParam) {
//        //HashMap param = new HashMap();
//        HashMap param = queryPageParam.getParam();
//        String name = (String) param.get("name");
//        String sex = (String) param.get("sex");
//        String person = (String) param.get("person");
//
//        Page<Storage> page = new Page<>();
//        page.setCurrent(queryPageParam.getPageNum());
//        page.setSize(queryPageParam.getPageSize());
//
//        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper();
//        if (StringUtils.isNotBlank(name) && !"null".equals(name)){
//            lambdaQueryWrapper.like(Storage::getName, name);
//        }
//        if (StringUtils.isNotBlank(sex)){
//            lambdaQueryWrapper.like(Storage::getSex, sex);
//        }
//        if (StringUtils.isNotBlank(person)){
//            lambdaQueryWrapper.eq(Storage::getPerson, person);
//        }
//
////        IPage result=storage2Service.pageC(page);
//        //使用 Wrapper 自定义SQL
//        IPage result = storageService.pageCC(page, lambdaQueryWrapper);
//
//        System.out.println("total:" + result.getTotal());
//
//        return Result.successful(result.getRecords(), result.getTotal());
//    }
//}
//
