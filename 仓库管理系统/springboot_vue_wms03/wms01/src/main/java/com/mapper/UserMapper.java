package com.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author leo
 * @since 2022-11-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage pageC(IPage<User> page, LambdaQueryWrapper<User> userLambdaQueryWrapper);
}
