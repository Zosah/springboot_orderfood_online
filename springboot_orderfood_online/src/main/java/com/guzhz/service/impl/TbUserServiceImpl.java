package com.guzhz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guzhz.entity.TbUser;
import com.guzhz.mapper.TbUserMapper;
import com.guzhz.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-24
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService , UserDetailsService{

    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    public List<TbUser> selectAllUser() {
        return tbUserMapper.selectList(null);
    }

    @Override
    public int insertUser(TbUser user) {
        return tbUserMapper.insert(user);
    }

    @Override
    public TbUser selectUserById(int uId) {
        return tbUserMapper.selectById(uId);
    }

    @Override
    public int updateUserByUser(TbUser user) {
        return tbUserMapper.updateById(user);
    }

    @Override
    public int deleteUserById(int uId) {
        return tbUserMapper.deleteById(uId);
    }

    @Override
    public List<TbUser> selectUserByName(String uUsername) {
        QueryWrapper<TbUser> wrapper=new QueryWrapper<>();
        wrapper.eq("u_username",uUsername);
        List<TbUser> users = tbUserMapper.selectList(wrapper);
        return users;
    }

    @Override
    public void selectPage(Page page) {
        tbUserMapper.selectPage(page, null);
    }


    //security验证用户名
    @Override
    public UserDetails loadUserByUsername(String uUsername) throws UsernameNotFoundException {
        //我们自己定义的 TbUserServiceImpl 需要实现 UserDetailsService 接口，实现该接口，就要实现接口中的方法，
        // 也就是 loadUserByUsername ，这个方法的参数就是用户在登录的时候传入的用户名，
        // 根据用户名去查询用户信息（查出来之后，系统会自动进行密码比对）

        //根据前端传过来的uUsername找到对应的user
        TbUser user = tbUserMapper.selectOne(new QueryWrapper<TbUser>().eq("u_username", uUsername));
        if (user==null){
            throw new UsernameNotFoundException("用户不存在!");
        }
        return user;
    }

}
