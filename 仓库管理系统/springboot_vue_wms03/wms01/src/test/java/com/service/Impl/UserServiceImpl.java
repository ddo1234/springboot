package com.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pojo.User;
import com.mapper.UserMapper;
import com.service.UserService;
//import com.service.impl.UserServiceImpl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


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


    private UserMapper userMapper;


    @Override
    public IPage pageC(IPage<User> page, LambdaQueryWrapper<User> userLambdaQueryWrapper) {
        return userMapper.pageC(page, userLambdaQueryWrapper);
    }
}
