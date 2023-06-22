package com.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface RecordMapper extends BaseMapper<Record> {

    List listStorage();

    IPage RPage(IPage<Record> page, @Param(Constants.WRAPPER) Wrapper<Record> wrapper);

    List listGoodsType();
}
