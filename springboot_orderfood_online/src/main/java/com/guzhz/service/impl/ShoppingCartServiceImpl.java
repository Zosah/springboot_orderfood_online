package com.guzhz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guzhz.entity.ShoppingCart;
import com.guzhz.mapper.ShoppingCartMapper;
import com.guzhz.service.ShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-30
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

    @Resource
    ShoppingCartMapper shoppingCartMapper;

    @Override
    public List<ShoppingCart> selectAllByUId(int uId) {
        return shoppingCartMapper.selectList(new QueryWrapper<ShoppingCart>().eq("u_id",uId));
    }

    @Override
    public ShoppingCart selectOneByMdIdAndUId(int mdId, int uId) {
        return shoppingCartMapper.selectOne(new QueryWrapper<ShoppingCart>().eq("md_id",mdId).eq("u_id",uId));
    }

    @Override
    public int insertProduct(ShoppingCart shoppingCart) {
        return shoppingCartMapper.insert(shoppingCart);
    }

    @Override
    public int updateProduct(ShoppingCart shoppingCart) {
        return shoppingCartMapper.updateById(shoppingCart);
    }

    @Override
    public int deleteById(int scId) {
        return shoppingCartMapper.deleteById(scId);
    }

    @Override
    public int selectExist(int mdId, int uId) {
        return shoppingCartMapper.selectCount(new QueryWrapper<ShoppingCart>().eq("md_id",mdId).eq("u_id",uId));
    }
}
