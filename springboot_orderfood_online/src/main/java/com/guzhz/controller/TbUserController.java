package com.guzhz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guzhz.entity.MenuDetail;
import com.guzhz.entity.TbUser;
import com.guzhz.service.impl.TbUserServiceImpl;
import com.guzhz.utils.FileUpload;
import com.guzhz.utils.UpdateSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-24
 */
@Controller
public class TbUserController {

    @Resource
    TbUserServiceImpl tbUserService;


    @GetMapping("/toLogin")
    public String toLogin(){
        return "user/login";
    }


    @PostMapping("/register")
    public String register(String uUsername, String uPassword, String uRole,Model model){
        List<TbUser> users = tbUserService.selectAllUser();
        for (TbUser temp : users){  //验证成功，直接登录
            if(uUsername.equals(temp.getUUsername())){
                model.addAttribute("msg","刚刚：注册失败，该用户已存在！");
                return "user/login";
            }
        }
        TbUser user = new TbUser();
        user.setUUsername(uUsername);
        user.setUPassword(uPassword);
        user.setURole(uRole);
        tbUserService.insertUser(user);
        model.addAttribute("msg","刚刚：注册成功，赶快登录进行登录吧~");
        return "user/login";
    }

    /*插入用户数据，返回字符串信息*/
    @ResponseBody
    @RequestMapping("/insertUserInfo")
    public String insertUserInfo(String uUsername, String uPassword, String uRole){
        //查询是否存在用户名了
        String msg = "未知错误！";
        List<TbUser> users = tbUserService.selectUserByName(uUsername);
        if(users.size() == 0){
            TbUser user = new TbUser();
            user.setUUsername(uUsername);
            user.setUPassword(uPassword);
            user.setURole(uRole);  //字符串转化为int
            int i = tbUserService.insertUser(user);
            if(i != 0){
                msg = "新添用户成功！";
            }
        }else {
            msg = "该用户名已存在，插入失败！";
        }
        return msg;
    }

    /*编辑用户信息，返回字符串信息*/
    @ResponseBody
    @RequestMapping("/edUserInfo")
    public String edUserInfo(TbUser user){
        tbUserService.updateUserByUser(user);
        return "用户数据已更新！";
    }

    /*跳转用户信息页面  +  分页*/
    @RequestMapping("/userInfo")     //从1开始
    public String userInfo(Model model,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        List<TbUser> users = tbUserService.selectAllUser();

        Page<TbUser> page = new Page<>(pageNum,5);
        tbUserService.selectPage(page);

        model.addAttribute("users",users);
        model.addAttribute("page",page);
        return "user/userInfo";
    }

    /*删除用户*/
    @RequestMapping("/deleteUser/{uId}")
    public String deleteUser(@PathVariable("uId")int uId, Model model){
        tbUserService.deleteUserById(uId);
        model.addAttribute("msg","删除成功！");
        return "redirect:/userInfo";
    }

    @GetMapping("/myInfo")
    public String myInfo(){
        return "user/myInfo";
    }

    /*上传头像*/
    @RequestMapping("/updateMyImg")
    public String updateMyImg(@RequestParam(value = "updateHeadImg") MultipartFile updateHeadImg,
                              int uId, Model model, HttpSession session){
        String imgPath = FileUpload.uploadFile(updateHeadImg);
        //更新操作
        TbUser user = tbUserService.selectUserById(uId);
        user.setUUrl(imgPath);
        tbUserService.updateUserByUser(user);

        user = tbUserService.selectUserById(uId);
        UpdateSession.reloadUserAuthority(session,user);
        return "redirect:/myInfo";
    }

    /*更新我的信息*/
    @RequestMapping("/updateMyInfo")
    public String updateMyInfo(TbUser user, HttpSession session){
        //更新操作
        TbUser tbUser = tbUserService.selectUserById(user.getUId());    //保存新数据，避免被原数据覆盖
        System.out.println("tbUser = " + tbUser);
        user.setUUsername(tbUser.getUUsername());
        user.setUPassword(tbUser.getPassword());
        user.setUMoney(tbUser.getUMoney());
        tbUserService.updateUserByUser(user);

        user = tbUserService.selectUserById(user.getUId());
        UpdateSession.reloadUserAuthority(session,user);
        return "redirect:/myInfo";
    }


//    @GetMapping("/outLogin")
//    public String outLogin(HttpSession session){
//        session.removeAttribute("user");
//        return "user/login";
//    }


//    @PostMapping("/login")   添加了安全策略不在需要手动验证，只需要提供个请求路径
//    public void login(String uUsername, String uPassword, Model model, HttpSession session){
//        List<TbUser> users = tbUserService.selectAllUser();
//        for (TbUser temp : users){  //验证成功，直接登录
//            if(uUsername.equals(temp.getUUsername()) && uPassword.equals(temp.getUPassword())){
//                session.setAttribute("user",temp);
//                return "redirect:/";
//            }
//            if(uUsername.equals(temp.getUUsername()) && !uPassword.equals(temp.getUPassword())){
//                model.addAttribute("msg","刚刚：密码错误，登录失败！");
//                return "user/login";
//            }
//        }
//        model.addAttribute("msg","刚刚：该用户不存在，请重新输入！");
//        return "user/login";
//    }
}

