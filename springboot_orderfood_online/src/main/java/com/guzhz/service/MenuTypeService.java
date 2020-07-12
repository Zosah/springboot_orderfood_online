package com.guzhz.service;

import com.guzhz.entity.MenuType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-26
 */
public interface MenuTypeService extends IService<MenuType> {

    int insertType(MenuType menuType);

    int deleteType(int mtId);

    int updateType(MenuType menuType);

    List<MenuType> selectAllType();

    MenuType selectOneType(int mtId);
}
