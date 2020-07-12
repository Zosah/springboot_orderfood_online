package com.guzhz.mapper;

import com.guzhz.entity.MenuDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guzhz.entity.MenuType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-26
 */
@Mapper
public interface MenuDetailMapper extends BaseMapper<MenuDetail> {

    List<MenuDetail> selectByType(int mtId);
}
