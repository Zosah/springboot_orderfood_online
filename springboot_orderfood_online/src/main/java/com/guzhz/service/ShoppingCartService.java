package com.guzhz.service;

import com.guzhz.entity.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-30
 */
public interface ShoppingCartService extends IService<ShoppingCart> {

    /*用过当前用户查询所有购物车的商品*/
    List<ShoppingCart> selectAllByUId(int uId);

    /*查询指定购物车产品（1条）*/
    ShoppingCart selectOneByMdIdAndUId(int mdId, int uId);

    /*把商品加入购物车 int mdId, int uId*/
    int insertProduct(ShoppingCart shoppingCart);

    /*更新单品 ByMdIdAndUId*/
    int updateProduct(ShoppingCart shoppingCart);

    /*删除当品*/
    int deleteById(int scId);

    /*查询是否存在于购物车*/
    int selectExist(int mdId, int uId);
}
