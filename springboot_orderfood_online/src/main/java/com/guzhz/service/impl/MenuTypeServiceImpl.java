package com.guzhz.service.impl;

import com.guzhz.entity.MenuType;
import com.guzhz.mapper.MenuTypeMapper;
import com.guzhz.service.MenuTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-26
 */
@Service
public class MenuTypeServiceImpl extends ServiceImpl<MenuTypeMapper, MenuType> implements MenuTypeService {

    @Resource
    MenuTypeMapper menuTypeMapper;

    @Override
    public int insertType(MenuType menuType) {
        return menuTypeMapper.insert(menuType);
    }

    @Override
    public int deleteType(int mtId) {
        return menuTypeMapper.deleteById(mtId);
    }

    @Override
    public int updateType(MenuType menuType) {
        return menuTypeMapper.updateById(menuType);
    }

    @Override
    public List<MenuType> selectAllType() {
        return menuTypeMapper.selectList(null);
    }

    @Override
    public MenuType selectOneType(int mtId) {
        return menuTypeMapper.selectById(mtId);
    }
}
