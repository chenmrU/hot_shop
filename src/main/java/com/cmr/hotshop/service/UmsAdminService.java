package com.cmr.hotshop.service;

import com.cmr.hotshop.entity.UmsAdmin;
import com.cmr.hotshop.entity.UmsPermission;

import java.util.List;

/**
 * @author chenmengrui
 * @Description: 后台管理员Service
 * @date 2019/12/26 19:49
 */
public interface UmsAdminService {

    /**
     * 根据用户名获取后台管理员
     * @param name
     * @return
     */
    UmsAdmin getAdminByUserName(String name);

    /**
     * 注册
     * @param umsAdmin
     * @return
     */
    UmsAdmin register(UmsAdmin umsAdmin);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 获取用户权限
     * @param adminId
     * @return
     */
    List<UmsPermission> getPermissionList(long adminId);

}
