package com.guzhz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guzhz.entity.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-24
 */
public interface TbUserService extends IService<TbUser> {

    List<TbUser> selectAllUser();

    int insertUser(TbUser user);

    TbUser selectUserById(int uId);

    int updateUserByUser(TbUser user);

    int deleteUserById(int uId);

    List<TbUser> selectUserByName(String uUsername);

    //分页
    void selectPage(Page page);

}
