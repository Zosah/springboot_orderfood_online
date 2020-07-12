package com.guzhz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guzhz.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-30
 */
@Mapper
public interface OrderDetailService extends IService<OrderDetail> {

    int insertOrder(OrderDetail orderDetail);

    int updateOrder(OrderDetail orderDetail);

    List<OrderDetail> selectAllOrderByUId(int uId);

    OrderDetail selectOneByOdId(int odId);

    List<OrderDetail> selectAll();

    void selectPage(Page page);
}
