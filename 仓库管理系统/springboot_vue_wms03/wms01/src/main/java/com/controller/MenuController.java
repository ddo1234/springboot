package com.controller;


import com.common.Result;
import com.pojo.Menu;
import com.pojo.User;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author leo
 * @since 2022-10-04
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    //动态路由实现按权限显示页面
    @GetMapping("/list")
    public Result list(@RequestParam String roleId) {
        List list = menuService.lambdaQuery().like(Menu::getMenuright, roleId).list();
        return Result.successful(list);
    }
}
