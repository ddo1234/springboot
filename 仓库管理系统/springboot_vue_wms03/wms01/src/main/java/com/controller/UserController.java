package com.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.QueryPageParam;
import com.common.Result;
import com.pojo.Menu;
import com.pojo.User;
import com.service.MenuService;
import com.service.UserService;
//import com.service.impl.UserService;
import com.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 12
 * @since 2022-11-24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/findByUserName")
    public Result findByUserName(@RequestParam String userName) {
        List list = userService.lambdaQuery().eq(User::getUserName, userName).list();
        return list.size() > 0 ? Result.successful(list) : Result.fail();
    }

    //增
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return userService.save(user) ? Result.successful() : Result.fail();
    }

    //删
//    @DeleteMapping("{id}")
//    public boolean delete(@PathVariable Integer id){
//        return userService.removeById(id);
//    }
    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        Boolean result = userService.removeById(id);
        return result ? Result.successful() : Result.fail();
    }

    //改
//    @PutMapping
//    public boolean saveOrUpdate(@RequestBody User user){
//        return userService.saveOrUpdate(user);
//    }
    @PostMapping("/update")
    public Result Update(@RequestBody User user) {
        return userService.updateById(user) ? Result.successful() : Result.fail();
    }

    //查
//    @PostMapping("/listU")
//    public Result listU(@RequestBody User user){
//        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        userLambdaQueryWrapper.like(User::getUserName,user.getUserName());    //Lambda静态方法引用  类名::方法名
//        return Result.suc(userService.list(userLambdaQueryWrapper));
//    }
    @PostMapping("/listU")
    public Result listU(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(user.getUserName())) {
            lambdaQueryWrapper.like(User::getUserName, user.getUserName());
        }
        return Result.successful(userService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPageC1")
    public Result listPageC1(@RequestBody QueryPageParam queryPageParam) {
        //HashMap param = new HashMap();
        HashMap param = queryPageParam.getParam();
        String userName = (String) param.get("userName");
        String roleId = (String) param.get("roleId");
        Page<User> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(userName) && !"null".equals(userName)) {
            userLambdaQueryWrapper.like(User::getUserName, userName);
        }
        if (StringUtils.isNotBlank(roleId)) {
            userLambdaQueryWrapper.eq(User::getRoleId, roleId);
        }
        //使用 Wrapper 自定义SQL
        IPage result = userService.page(page, userLambdaQueryWrapper);
        return Result.successful(result.getRecords(), result.getTotal());
    }


    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {

        List list = userService.lambdaQuery()
                .eq(User::getUserName, user.getUserName())       //判断该账号是否正确
                .eq(User::getPassword, user.getPassword()).list();       //判断该密码是否正确
        if (list.size() > 0) {
            User user1 = (User) list.get(0);
            List menuList = menuService.lambdaQuery().like(Menu::getMenuright, user1.getRoleId()).list();
            HashMap res = new HashMap();
            res.put("user", user1);
            res.put("menu", menuList);
            return Result.successful(res);
        } else {
            return Result.fail();
        }

    }

}
