package com.guzhz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guzhz.entity.MenuDetail;
import com.guzhz.mapper.MenuDetailMapper;
import com.guzhz.service.MenuDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MenuDetailServiceImpl extends ServiceImpl<MenuDetailMapper, MenuDetail> implements MenuDetailService {

    @Resource
    MenuDetailMapper menuDetailMapper;

    @Override
    public List<MenuDetail> selectAllMenu() {
        return menuDetailMapper.selectList(null);
    }

    @Override
    public List<MenuDetail> selectByType(int mtId) {
        return menuDetailMapper.selectByType(mtId);
    }



    @Override
    public void selectPage(Page page, int mtId) {
        QueryWrapper<MenuDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("mt_id",mtId);
        if(mtId == 0){
            menuDetailMapper.selectPage(page,null);
        }else{
            menuDetailMapper.selectPage(page,wrapper);
        }
    }

    @Override
    public void selectPageByName(Page page, String search) {
        QueryWrapper<MenuDetail> wrapper = new QueryWrapper<>();
        wrapper.like("md_name",search);
        menuDetailMapper.selectPage(page,wrapper);
    }

    @Override
    public int deleteOneMenu(int mdId) {
        return menuDetailMapper.deleteById(mdId);
    }

    @Override
    public int updateMenu(MenuDetail menuDetail) {
        return menuDetailMapper.updateById(menuDetail);
    }

    @Override
    public MenuDetail selectById(int mdId) {
        return menuDetailMapper.selectById(mdId);
    }

    @Override
    public int insertMenu(MenuDetail menuDetail) {
        return menuDetailMapper.insert(menuDetail);
    }


}
