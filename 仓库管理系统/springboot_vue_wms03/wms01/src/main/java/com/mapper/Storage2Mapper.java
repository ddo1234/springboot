package com.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.Storage2;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author leo
 * @since 2022-11-27
 */
@Mapper
public interface Storage2Mapper extends BaseMapper<Storage2> {
    //注意事项:需要mybatis-plus版本 >= 3.0.7 param 参数名要么叫ew,要么加上注解@Param(Constants.WRAPPER)
    //这里使用的是注解
    IPage pageQ(Page<Storage2> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    List findUsers();
}
