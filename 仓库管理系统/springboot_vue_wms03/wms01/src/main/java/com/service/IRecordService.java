package com.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pojo.Record;

import java.util.List;

public interface IRecordService extends IService<Record> {


    IPage RPage(IPage<Record> page, Wrapper<Record> wrapper);

    List listGoodsType();

    List listStorage();
}
