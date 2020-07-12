package com.guzhz.controller;


import com.guzhz.entity.MenuDetail;
import com.guzhz.entity.MenuType;
import com.guzhz.service.impl.MenuDetailServiceImpl;
import com.guzhz.service.impl.MenuTypeServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-26
 */
@RestController
public class MenuTypeController {

    @Resource
    MenuTypeServiceImpl menuTypeService;

    @Resource
    MenuDetailServiceImpl menuDetailService;

    @RequestMapping("/insertType")
    public String insertType(MenuType menuType){
        menuTypeService.insertType(menuType);
        return "成功添加新分类："+menuType.getMtName();
    }

    @RequestMapping("/updateType")
    public String updateType(MenuType menuType){
        System.out.println("menuType = " + menuType);
        menuTypeService.updateType(menuType);
        return "修改分类名成功："+menuType.getMtName();
    }

    @RequestMapping("/deleteType")
    public String deleteType(int mtId){
        System.out.println("mtId = " + mtId);
        //查询是否该分类存在菜品，存在则设定不给删除
        MenuType type = menuTypeService.selectOneType(mtId);
        List<MenuDetail> list = menuDetailService.selectByType(mtId);
        if(list.size() != 0){
            return type.getMtName() + "存在菜品，删除失败！";
        }
        menuTypeService.deleteType(mtId);
        return type.getMtName() + "删除成功！";
    }
}

