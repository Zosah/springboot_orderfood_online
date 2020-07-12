# springboot_orderfood_online
基于SpringBoot的在线订餐系统

## 搭建环境
Idea + MySql8.0.18 + Springboot + MybatisPlus + BootStrap + Thymeleaf + Security5

## 项目描述
具体图片可查看“演示ppt”
- 该系统为在线订餐系统，类似pc端的外卖订购平台。角色分为用户、管理员两个角色。
- 用户拥有对菜品的加入购物车、下单、查看订单，查看个人信息等功能。
- 管理员可以对菜单进行管理，菜单又可以进行分类，分类管理、用户管理。


## 图片的加载说明
配置绝对路径，然后用映射路径进行访问，不同的电脑需要修改以下路径。

```java
public class MyWebAppConfigurer 
    gistry.addResourceHandler("/images/**").addResourceLocations("file:F:/Study/Book6/JavaEE/images/"); 
//修改为你的绝对路径
```
```java
public class FileUpload
    String filePath = "F:\\Study\\Book6\\JavaEE\\images";
//修改为你的绝对路径
```

