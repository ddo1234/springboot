package com.service.impl;

import com.mapper.GoodsMapper;
import com.pojo.Goods;
import com.service.GoodsService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ash
 * @since 2022-11-24
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public IPage page1(IPage<Goods> page, Wrapper wrapper) {
        return goodsMapper.page1(page, wrapper);
    }

    @Override
    public List findType() {
        return goodsMapper.findType();
    }

    @Override
    public List findStorage() {
        return goodsMapper.findStorage();
    }

    @Override
    public List findTotal() {
        return goodsMapper.findTotal();
    }

    @Override
    public List<Goods> findLess() {
        return goodsMapper.findLess();
    }

    @Override
    public Integer getCount() {
        return goodsMapper.getCount();
    }


}
