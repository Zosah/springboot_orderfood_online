package com.guzhz.mapper;

import com.guzhz.entity.TbUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-24
 */
@Mapper
public interface TbUserMapper extends BaseMapper<TbUser> {

}
