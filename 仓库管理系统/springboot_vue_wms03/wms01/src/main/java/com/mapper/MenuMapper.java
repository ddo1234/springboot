package com.mapper;

import com.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.Mapping;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author leo
 * @since 2022-12-04
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}
