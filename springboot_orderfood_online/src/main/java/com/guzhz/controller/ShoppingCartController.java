package com.guzhz.controller;


import com.guzhz.entity.ShoppingCart;
import com.guzhz.service.impl.ShoppingCartServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-30
 */
@Controller
public class ShoppingCartController {

    @Resource
    ShoppingCartServiceImpl shoppingCartService;

    /*去我的购物车页面*/
    @RequestMapping("/shoppingCart")
    public String shoppingCart(Model model,int uId){

        //查询购物车的商品
        List<ShoppingCart> products = shoppingCartService.selectAllByUId(uId);
        int total = 0;
        for (ShoppingCart cart : products){
            total += cart.getScPrice()*cart.getScAmount();
        }
        model.addAttribute("products",products);
        model.addAttribute("total",total);
        return "user/shoppingCart";
    }

    @RequestMapping("/shoppingCart-plus")
    public String shoppingCartPlus(int uId,int mdId){
        //把数量+1
        ShoppingCart one = shoppingCartService.selectOneByMdIdAndUId(mdId, uId);
        one.setScAmount(one.getScAmount()+1);
        shoppingCartService.updateProduct(one);
        System.out.println("数量+1成功");
        return "redirect:/shoppingCart?uId="+uId;
    }

    @RequestMapping("/shoppingCart-minus")
    public String shoppingCartMinus(int uId,int mdId){
        /*如果 数量== 1，直接删除*/
        ShoppingCart one = shoppingCartService.selectOneByMdIdAndUId(mdId, uId);
        if (one.getScAmount() == 1 ) {
            //删除数据
            shoppingCartService.deleteById(one.getScId());
            return "redirect:/shoppingCart?uId="+uId;
        }else{
            //数量-1
            one.setScAmount(one.getScAmount()-1);
            shoppingCartService.updateProduct(one);
            System.out.println("数量-1成功");
            return "redirect:/shoppingCart?uId="+uId;
        }
    }

    @ResponseBody
    @RequestMapping("/addShoppingCart")
    public String addShoppingCart(ShoppingCart shoppingCart){
        //插入一条数据
        if (shoppingCartService.selectExist(shoppingCart.getMdId(), shoppingCart.getUId()) == 0 ) {
            shoppingCartService.insertProduct(shoppingCart);
            return "新加入成功！";
        }else{
            /*如果已经存在了，直接加一*/
            ShoppingCart one = shoppingCartService.selectOneByMdIdAndUId(shoppingCart.getMdId(), shoppingCart.getUId());
            one.setScAmount(one.getScAmount()+1);
            shoppingCartService.updateProduct(one);
            return "数量加+1成功！";
        }
    }
}

