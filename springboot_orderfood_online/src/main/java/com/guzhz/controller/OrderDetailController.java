package com.guzhz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guzhz.entity.MenuDetail;
import com.guzhz.entity.OrderDetail;
import com.guzhz.entity.ShoppingCart;
import com.guzhz.entity.TbUser;
import com.guzhz.service.impl.MenuDetailServiceImpl;
import com.guzhz.service.impl.OrderDetailServiceImpl;
import com.guzhz.service.impl.ShoppingCartServiceImpl;
import com.guzhz.service.impl.TbUserServiceImpl;
import com.guzhz.utils.RandomNo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collections;
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
public class OrderDetailController {

    @Resource
    OrderDetailServiceImpl orderDetailService;

    @Resource
    ShoppingCartServiceImpl shoppingCartService;

    @Resource
    MenuDetailServiceImpl menuDetailService;

    @Resource
    TbUserServiceImpl tbUserService;

    //修改订单状态，从0到1，由商家更新
    @ResponseBody
    @RequestMapping("/changeStatusToOne")
    public String changeStatusToOne(int odId){
        OrderDetail one = orderDetailService.selectOneByOdId(odId);
        one.setOdStatus(1);
        orderDetailService.updateOrder(one);    //更新订单
        return "已接单，尽快送餐吧~";
    }
    //修改订单状态，从1到2，由用户更新
    @ResponseBody
    @RequestMapping("/changeStatusToTwo")
    public String changeStatusToTwo(int odId){
        OrderDetail one = orderDetailService.selectOneByOdId(odId);
        one.setOdStatus(2);
        orderDetailService.updateOrder(one);    //更新订单
        //同时更新商家的账户
        TbUser adminUser = tbUserService.selectUserById(1);

        int updateMoney = one.getOdTotal()+adminUser.getUMoney();
        adminUser.setUMoney(updateMoney);
        tbUserService.updateUserByUser(adminUser);
        return "您已确认送达，钱已入商家商户，订单已完结~";
    }

    @RequestMapping("/orderManage")
    public String orderManage(Model model,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        List<OrderDetail> orders = orderDetailService.selectAll();
        Collections.reverse(orders);    //倒叙

        Page<TbUser> page = new Page<>(pageNum,10);
        orderDetailService.selectPage(page);    //分页

        int allCount = orders.size();   //总数
        int pendingCount = 0;           //待处理单数
        int receivedCount = 0;          //待已接单数
        int finishCount = 0;            //已完结
        for (OrderDetail order : orders){
            if(order.getOdStatus() == 0){
                pendingCount += 1;
            }else if(order.getOdStatus() == 1){
                receivedCount += 1;
            }else{
                finishCount += 1;
            }
        }
        TbUser adminUser = tbUserService.selectUserById(1);
        model.addAttribute("total",adminUser.getUMoney());
        model.addAttribute("page",page);
        model.addAttribute("orders",orders);
        model.addAttribute("allCount",allCount);
        model.addAttribute("pendingCount",pendingCount);
        model.addAttribute("receivedCount",receivedCount);
        model.addAttribute("finishCount",finishCount);
        return "menu/orderManage";
    }


    @RequestMapping("/myOrder")
    public String myOrder(Model model,int uId){
        List<OrderDetail> myOrders = orderDetailService.selectAllOrderByUId(uId);
        TbUser user = tbUserService.selectUserById(uId);
        model.addAttribute("myOrders",myOrders);
        model.addAttribute("total",user.getUMoney());
        return "user/myOrder";
    }

    @ResponseBody
    @PostMapping("/toOrder")
    public String toOrder(OrderDetail orderDetail){
        //查询当前用户的所以购物车
        List<ShoppingCart> carts = shoppingCartService.selectAllByUId(orderDetail.getUId());
        //获取到具体商品
        String odDetail = "【";
        int odTotal = 0;
        for (ShoppingCart cart : carts){
            odDetail += cart.getScName()+" × "+cart.getScAmount()+";"; //拼接成：米饭 × 1;格式
            odTotal += cart.getScPrice()*cart.getScAmount();    //数量×价格
        }
        odDetail += "】";
        //赋值
        orderDetail.setOdDetail(odDetail);
        orderDetail.setOdTotal(odTotal);
        orderDetail.setOdStatus(0);
        orderDetail.setOdNo(RandomNo.getRandomNo());
        //完成插入
        orderDetailService.insertOrder(orderDetail);
        //清空购物车 + 更新商品数据
        for (ShoppingCart cart : carts){
            shoppingCartService.deleteById(cart.getScId());                         //删除购物车数据
            MenuDetail menuDetail = menuDetailService.selectById(cart.getMdId());   //通过产品id获得产品
            menuDetail.setMdAmount(menuDetail.getMdAmount()-cart.getScAmount());    //减去下单数量
            menuDetailService.updateMenu(menuDetail);                               //更新产品数据
        }
        TbUser user = tbUserService.selectUserById(orderDetail.getUId());              //用户更新支付金额
        user.setUMoney(user.getUMoney()+odTotal);
        tbUserService.updateUserByUser(user);
        List<OrderDetail> details = orderDetailService.selectAllOrderByUId(orderDetail.getUId());
        for (OrderDetail detail : details){
            System.out.println("detail = " + detail.getOdCreateTime());
        }
        return "下单成功~";
    }
}

