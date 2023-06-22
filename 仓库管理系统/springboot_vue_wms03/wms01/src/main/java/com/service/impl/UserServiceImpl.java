package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pojo.User;
import com.mapper.UserMapper;
import com.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 12
 * @since 2022-11-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //    @Autowired
    @Resource
    private UserMapper userMapper;


    @Override
    public IPage pageC(IPage<User> page, LambdaQueryWrapper<User> userLambdaQueryWrapper) {
        return userMapper.pageC(page, userLambdaQueryWrapper);
    }
}
