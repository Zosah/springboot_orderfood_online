package com.guzhz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guzhz.entity.MenuDetail;
import com.guzhz.entity.MenuType;
import com.guzhz.service.impl.MenuDetailServiceImpl;
import com.guzhz.service.impl.MenuTypeServiceImpl;
import com.guzhz.utils.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-26
 */
@Controller
public class MenuDetailController {

    @Resource
    MenuDetailServiceImpl menuDetailService;

    @Resource
    MenuTypeServiceImpl menuTypeService;

    @Resource
    FileUpload fileUpload;

    /*主页*/ /*联合查询，根据menuId查询 */
    @RequestMapping({"/main","/","/index"})
    public String main(Model model,HttpSession session){
        //用于封装整合后的对象的集合
        List<MenuDetail> menus = new ArrayList<MenuDetail>();
        List<MenuType> types = menuTypeService.selectAllType(); //查询出所有类型对象

        //临时集合，用于存放需要移除的，0菜品的类型对象
        List<MenuType> temp = new ArrayList<MenuType>();
        //通过mt_id重新封装对象列表
        for (MenuType type : types){
            List<MenuDetail> menu = menuDetailService.selectByType(type.getMtId());
            //如果 当前分类没有菜品，则移除该分类，同时不添加
            if (menu.size() == 0){
                temp.add(type);
            }
            menus.addAll(menu);
        }
        //封装后的list
        model.addAttribute("menus",menus);

        //移除空的类型
        for (MenuType t : temp){
            types.remove(t);
        }
        model.addAttribute("types",types);

        return "main";
    }


    /*菜单管理 分页*/  /*新增模糊查询分页*/
    @RequestMapping("/menuManage")
    public String menuManage(@RequestParam(value = "mtId",defaultValue = "0") int mtId,
                             @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "msg",defaultValue = "no") String msg,           //上传图片传过来的值
                             @RequestParam(value = "search",defaultValue = "no") String search,
                             Model model){

        Page<MenuDetail> page = new Page<>(pageNum,3);

        //根据mtId进行分页。 mtId=0 默认显示全部
        menuDetailService.selectPage(page,mtId);

        //根据搜索名分页，覆盖原来的page；
        if(!search.equals("no")) {
            menuDetailService.selectPageByName(page,search);
            msg = "searchSuccess";
        }

        model.addAttribute("page",page);

        List<MenuType> types = menuTypeService.selectAllType();
        model.addAttribute("types",types);
        model.addAttribute("mtId",mtId);    //激活标签
        model.addAttribute("msg",msg);
        return "menu/menuManage";
    }

    /*删除单品*/
    @RequestMapping("/deleteOneMenu/{mdId}/{mtId}")
    public String deleteOneMenu(@PathVariable(value = "mdId")int mdId, @PathVariable(value = "mtId")int mtId){
        int i = menuDetailService.deleteOneMenu(mdId);
        return "redirect:/menuManage?mtId=" + mtId;
    }

    /*上传图片*/
    @RequestMapping("/upLoadImg")
    public String upLoadImg(@RequestParam(value = "upLoadFile") MultipartFile upLoadFile,
                            @RequestParam(value = "mdId")int mdId,
                            @RequestParam(value = "mtId")int mtId, Model model){
        String msg = null;
        String imgPath = FileUpload.uploadFile(upLoadFile);

        //更新操作
        MenuDetail menuDetail = menuDetailService.selectById(mdId);
        System.out.println("menuDetail = " + menuDetail.getMdName());
        menuDetail.setMdUrl(imgPath);
        menuDetailService.updateMenu(menuDetail);
        if(imgPath == null){
            msg = "uploadFault";
            return "redirect:/menuManage?mtId="+mtId+"&msg="+msg;
        }
        System.out.println("imgPath = " + imgPath);

        msg = "uploadSuccess";
        return "redirect:/menuManage?mtId="+mtId+"&msg="+msg;
    }

    @ResponseBody
    @RequestMapping("/insertMenu")
    public String insertMenu(MenuDetail menuDetail){
        menuDetailService.insertMenu(menuDetail);
        return "已成功新添菜品："+menuDetail.getMdName();
    }

    @ResponseBody
    @RequestMapping("/updateMenu")
    public String updateMenu(MenuDetail menuDetail){
        MenuDetail tempMenu = menuDetailService.selectById(menuDetail.getMdId());  //原来数据库中的数据
        tempMenu.setMdName(menuDetail.getMdName());
        tempMenu.setMdPrice(menuDetail.getMdPrice());
        tempMenu.setMdAmount(menuDetail.getMdAmount());
        tempMenu.setMdNew(menuDetail.getMdNew());
        tempMenu.setMdStar(menuDetail.getMdStar());
        tempMenu.setMtId(menuDetail.getMtId());         //重新赋值
        menuDetailService.updateMenu(tempMenu);
        return menuDetail.getMdName()+"更新成功！";
    }
}

