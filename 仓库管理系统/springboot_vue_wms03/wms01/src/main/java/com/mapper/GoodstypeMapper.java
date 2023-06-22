package com.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.Goodstype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface GoodstypeMapper extends BaseMapper<Goodstype> {

    IPage pageC(IPage<Goodstype> page);

    IPage pageCC(Page<Goodstype> page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
