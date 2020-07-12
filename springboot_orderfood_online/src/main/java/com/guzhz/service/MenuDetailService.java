package com.guzhz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guzhz.entity.MenuDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guzhz.entity.MenuType;
import com.guzhz.entity.TbUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-26
 */
public interface MenuDetailService extends IService<MenuDetail> {

    /*查询所有*/
    List<MenuDetail> selectAllMenu();

    /*根据类型查询*/
    List<MenuDetail> selectByType(int mtId);

    //通过类型，分页
    void selectPage(Page page, int mtId);

    //通过名字，分页
    void selectPageByName(Page page, String search);

    /*删除单个*/
    int deleteOneMenu(int mdId);

    /*更新*/
    int updateMenu(MenuDetail menuDetail);

    /*查询 通过自己的id*/
    MenuDetail selectById(int mdId);

    /*插入新菜品*/
    int insertMenu(MenuDetail menuDetail);
}
