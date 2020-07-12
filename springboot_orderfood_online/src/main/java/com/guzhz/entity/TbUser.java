package com.guzhz.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TbUser对象", description="")
public class TbUser implements Serializable, UserDetails {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "u_id", type = IdType.AUTO)
    private Integer uId;

    @ApiModelProperty(value = "用户名")
    private String uUsername;

    @ApiModelProperty(value = "密码")
    private String uPassword;

    @ApiModelProperty(value = "角色,admin为商家,user为顾客")
    private String uRole;

    @ApiModelProperty(value = "用户姓名")
    private String uName;

    @ApiModelProperty(value = "用户手机")
    private String uPhone;

    @ApiModelProperty(value = "用户地址")
    private String uAddress;

    @ApiModelProperty(value = "用户收支")
    private int uMoney;

    @ApiModelProperty(value = "头像")
    private String uUrl;

    @ApiModelProperty(value = "逻辑删除：默认为0")
    @TableLogic
    private Integer uDeleted;

    @ApiModelProperty(value = "乐观锁：默认值为1")
    @Version
    private Integer uVersion;


    //返回用户的角色信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities =new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(uRole));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.getUPassword();
    }

    @Override
    public String getUsername() {
        return this.getUUsername();
    }


    //账号没有过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账号没有被锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //密码没有过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
