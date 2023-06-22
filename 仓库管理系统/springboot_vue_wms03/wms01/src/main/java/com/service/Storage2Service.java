package com.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.Storage2;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author leo
 * @since 2022-11-27
 */
public interface Storage2Service extends IService<Storage2> {
    IPage pageQ(Page<Storage2> page, Wrapper wrapper);

    List findUsers();
}
