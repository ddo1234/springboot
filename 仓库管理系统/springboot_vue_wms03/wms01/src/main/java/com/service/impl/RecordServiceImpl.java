package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mapper.RecordMapper;
import com.pojo.Record;
import com.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {
    @Resource
    private RecordMapper recordMapper;

    @Override
    public List listStorage() {
        return recordMapper.listStorage();
    }

    @Override
    public IPage RPage(IPage<Record> page, Wrapper<Record> wrapper) {
        return recordMapper.RPage(page, wrapper);
    }

    @Override
    public List listGoodsType() {
        return recordMapper.listGoodsType();
    }
}
