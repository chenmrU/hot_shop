package com.cmr.hotshop.dto;

import cn.hutool.core.util.StrUtil;
import com.cmr.hotshop.entity.UmsAdmin;
import com.cmr.hotshop.entity.UmsPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenmengrui
 * @Description: SpringSecurity需要的用户详情
 * @date 2019/12/27 10:28
 */
public class AdminUserDetails implements UserDetails {

    private UmsAdmin admin;
    private List<UmsPermission> permissionList;

    public AdminUserDetails(UmsAdmin admin, List<UmsPermission> permissionList) {
        this.admin = admin;
        this.permissionList = permissionList;
    }

    /**
     * 返回当前用户权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissionList.stream()
                .filter(permission -> StrUtil.isNotBlank(permission.getValue()))
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
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
        return admin.getStatus().equals(1);
    }
}
