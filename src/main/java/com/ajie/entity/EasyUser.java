package com.ajie.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 定义User对象
 * @Author 阿杰
 * @create 20222
 */
@Data
public class EasyUser implements UserDetails {
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "状态")
    private boolean state;

    @ApiModelProperty(value = "微信获取用户头像")
    private String avatarUrl;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "微信小程序用户唯一标识")
    private String openId;

    @ApiModelProperty("微信获取用户地址")
    private String address;

    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @ApiModelProperty(value = "角色列表")
    private List<Role> roles;

    public Collection<? extends GrantedAuthority> getPremission() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(item -> {
            List<Premission> premissions = item.getPremissions();
            premissions.forEach(premission -> {
                authorities.add(new SimpleGrantedAuthority(premission.getPremission()));
            });
        });
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>(this.roles.size());
        this.roles.parallelStream().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isState();
    }
}
