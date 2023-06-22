package com.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 12
 * @since 2022-11-24
 */
public interface UserService extends IService<User> {

    IPage pageC(IPage<User> page, LambdaQueryWrapper<User> userLambdaQueryWrapper);
}
