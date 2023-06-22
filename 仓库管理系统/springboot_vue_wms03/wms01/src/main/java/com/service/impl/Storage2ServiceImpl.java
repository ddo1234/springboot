package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.mapper.StorageMapper;
//import com.pojo.Storage;
import com.pojo.Storage2;
import com.mapper.Storage2Mapper;
import com.service.Storage2Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author leo
 * @since 2022-11-27
 */
@Service
public class Storage2ServiceImpl extends ServiceImpl<Storage2Mapper, Storage2> implements Storage2Service {
    @Resource
    private Storage2Mapper storage2Mapper;

    @Override
    public IPage pageQ(Page<Storage2> page, Wrapper wrapper) {
        return storage2Mapper.pageQ(page, wrapper);
    }

    @Override
    public List findUsers() {
        return storage2Mapper.findUsers();
    }
}
