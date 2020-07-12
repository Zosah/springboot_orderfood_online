package com.guzhz;

import com.guzhz.entity.MenuDetail;
import com.guzhz.entity.MenuType;
import com.guzhz.entity.ShoppingCart;
import com.guzhz.entity.TbUser;
import com.guzhz.service.impl.MenuDetailServiceImpl;
import com.guzhz.service.impl.ShoppingCartServiceImpl;
import com.guzhz.service.impl.TbUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@MapperScan("com.guzhz.mapper")
@SpringBootTest
class SpringbootOrderfoodOnlineApplicationTests {

    @Autowired
    TbUserServiceImpl tbUserService;

    @Autowired
    MenuDetailServiceImpl menuDetailService;

    @Autowired
    ShoppingCartServiceImpl shoppingCartService;

    @Test
    void contextLoads() {}

    @Test
    public void test1(){    //测试查询所有用户
        List<TbUser> tbUsers = tbUserService.selectAllUser();
        tbUsers.forEach(System.out::println);
    }

    @Test
    public void test2(){     //测试插入用户
        TbUser user = new TbUser();
        user.setUUsername("guest2");
        user.setUPassword("123456");
        user.setURole("ROLE_user");
        int i = tbUserService.insertUser(user);
        System.out.println("i = " + i);
    }

    @Test
    public void test3(){    //测试乐观锁
        TbUser user1 = tbUserService.selectUserById(2);
        user1.setUName("hhh");

        TbUser user2 = tbUserService.selectUserById(2);
        user2.setUName("xxx");
        tbUserService.updateUserByUser(user2);

        tbUserService.updateUserByUser(user1);

        tbUserService.deleteUserById(2);
    }

    @Test      //测试根据名字查询
    public void test4(){
        List<TbUser> users = tbUserService.selectUserByName("guest2");
        users.forEach(System.out::println);
        System.out.println("users count = " + users.size());
    }

    @Test      //测试查询所有菜品
    public void test5(){
        List<MenuDetail> menus = menuDetailService.selectAllMenu();
        menus.forEach(System.out::println);
    }

    @Test   //测试多表查询
    public void test6(){
        List<MenuDetail> menus = menuDetailService.selectByType(1);
        menus.forEach(System.out::println);
    }


    @Test  //测试购物车功能
    public void test7(){
        int mdId = 1;
        int uId = 1;
        MenuDetail menuDetail = menuDetailService.selectById(mdId);

        //查询商品是否已被加入到购物车：mdId/uId
        if (shoppingCartService.selectExist(mdId, uId) == 0 ) {
            ShoppingCart sc = new ShoppingCart();
            sc.setScName(menuDetail.getMdName());
            sc.setScAmount(1);
            sc.setScPrice(menuDetail.getMdPrice());
            sc.setScImg(menuDetail.getMdUrl());
            sc.setMdId(mdId);
            sc.setUId(uId);
            //插入一条数据
            shoppingCartService.insertProduct(sc);
        }else{
            //插入一条数据
            ShoppingCart one = shoppingCartService.selectOneByMdIdAndUId(mdId, uId);
            one.setScAmount(one.getScAmount()+1);
            shoppingCartService.updateProduct(one);
            System.out.println("更新成功");
        }

//        List<ShoppingCart> carts = shoppingCartService.selectAllByUId(uId);
//        int priceTotal = 0;
//        for (ShoppingCart cart : carts){
//            priceTotal += cart.getScPrice();
//        }
//        System.out.println("priceTotal = " + priceTotal);

        /*如果 数量== 1，直接删除*/
//        ShoppingCart one = shoppingCartService.selectOneByMdIdAndUId(mdId, uId);
//        if (one.getScAmount() == 1 ) {
//            //删除一条数据
//            shoppingCartService.deleteById(one.getScId());
//        }else{
//            //数量-1
//            one.setScAmount(one.getScAmount()-1);
//            shoppingCartService.updateProduct(one);
//            System.out.println("更新成功");
//        }
    }

    //唯一订单号
    @Test String test8(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;
    }
}
