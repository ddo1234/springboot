package com.service;

import com.pojo.Goods;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ash
 * @since 2022-11-24
 */
public interface GoodsService extends IService<Goods> {

    IPage page1(IPage<Goods> page, Wrapper wrapper);


    List findType();

    List findStorage();

    List findTotal();


    List<Goods> findLess();

    Integer getCount();
}
