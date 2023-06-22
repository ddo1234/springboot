package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.Storage;
import com.mapper.StorageMapper;
import com.service.StorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author leo
 * @since 2022-11-26
 */
//@Service
//public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {
//    @Resource
//    private StorageMapper storageMapper;
//
//    @Override
//    public IPage pageC(IPage<Storage> page) {
//        return storageMapper.pageC(page);
//    }
//
//    @Override
//    public IPage pageCC(Page<Storage> page, Wrapper wrapper) {
//        return storageMapper.pageCC(page,wrapper);
//    }
//}
