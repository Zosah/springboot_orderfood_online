package com.guzhz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guzhz.entity.OrderDetail;
import com.guzhz.mapper.OrderDetailMapper;
import com.guzhz.service.OrderDetailService;
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
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Resource
    OrderDetailMapper orderDetailMapper;

    @Override
    public int insertOrder(OrderDetail orderDetail) {
        return orderDetailMapper.insert(orderDetail);
    }

    @Override
    public int updateOrder(OrderDetail orderDetail) {
        return orderDetailMapper.updateById(orderDetail);
    }

    @Override
    public List<OrderDetail> selectAllOrderByUId(int uId) {
        return orderDetailMapper.selectList(new QueryWrapper<OrderDetail>().eq("u_Id",uId));
    }

    @Override
    public OrderDetail selectOneByOdId(int odId) {
        return orderDetailMapper.selectOne(new QueryWrapper<OrderDetail>().eq("od_id",odId));
    }

    @Override
    public List<OrderDetail> selectAll() {
        return orderDetailMapper.selectList(null);
    }

    @Override
    public void selectPage(Page page) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("od_createTime");
        orderDetailMapper.selectPage(page, queryWrapper);
    }
}
