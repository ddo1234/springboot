package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mapper.GoodstypeMapper;
import com.pojo.Goodstype;
import com.service.GoodstypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class GoodstypeServiceImpl extends ServiceImpl<GoodstypeMapper, Goodstype> implements GoodstypeService {

    @Resource
    private GoodstypeMapper goodstypeMapper;

    @Override
    public IPage pageC(IPage<Goodstype> page) {
        return goodstypeMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<Goodstype> page, Wrapper wrapper) {
        return goodstypeMapper.pageCC((Page<Goodstype>) page, wrapper);
    }
}
