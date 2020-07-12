package com.guzhz.config;

import com.guzhz.service.TbUserService;
import com.guzhz.service.impl.TbUserServiceImpl;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.Resource;

/**
 * @author：Guzhz
 * @date ：2020/6/29 0:31
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    TbUserServiceImpl tbUserService;

    @Override   //授权规则
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/","/index","/main").permitAll()
                .antMatchers("/userInfo","/insertUserInfo","/edUserInfo","/deleteUser/**").hasAnyRole("admin")
                .antMatchers("/menuManage","/deleteOneMenu/**","/upLoadImg","/insertMenu","/updateMenu").hasAnyRole("admin")
                .antMatchers("/insertType","/updateType","/deleteType").hasAnyRole("admin")
                .antMatchers("/orderManage","/changeStatusToOne").hasAnyRole("admin")
                .antMatchers("/myInfo","/updateMyImg","/updateMyInfo").hasAnyRole("admin","user")
                .antMatchers("/shoppingCart","/shoppingCart-plus","/shoppingCart-minus","/addShoppingCart").hasAnyRole("user")
                .antMatchers("/toOrder","/myOrder","/changeStatusToTwo").hasAnyRole("user");

        http.formLogin().loginPage("/toLogin").usernameParameter("uUsername").passwordParameter("uPassword")
                .loginProcessingUrl("/login")   //表单提交的请求，可以不需要路由（走security的路由）
                .failureUrl("/toLogin?error=true");
                http.csrf().disable();  //取消ajax拦截（post），如果改为post/get则可能不会报错
        http.logout().logoutSuccessUrl("/toLogin");

        //解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8"); filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
/*
        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember");*/
    }

    @Override    //验证规则
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(tbUserService).passwordEncoder(new NoPasswordEncoder());

    }
}
