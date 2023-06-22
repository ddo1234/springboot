package com.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.Storage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author leo
 * @since 2022-11-26
 */
public interface StorageService extends IService<Storage> {
    IPage pageC(IPage<Storage> page);

    IPage pageCC(Page<Storage> page, Wrapper wrapper);
}
