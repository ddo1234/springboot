package com.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pojo.Goodstype;


public interface GoodstypeService extends IService<Goodstype> {
    IPage pageC(IPage<Goodstype> page);

    IPage pageCC(IPage<Goodstype> page, Wrapper wrapper);
}
