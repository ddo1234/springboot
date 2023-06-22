package com.mapper;

import com.pojo.Goods;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ash
 * @since 2022-11-24
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    IPage page1(IPage<Goods> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    List findType();

    List findStorage();

    List findTotal();


    List<Goods> findLess();

    Integer getCount();
}
