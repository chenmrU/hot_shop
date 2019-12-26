package com.cmr.hotshop.service.impl;

import com.cmr.hotshop.entity.UmsAdmin;
import com.cmr.hotshop.entity.UmsPermission;
import com.cmr.hotshop.service.UmsAdminService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenmengrui
 * @Description: 后台管理员Service
 * @date 2019/12/26 19:53
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    @Override
    public UmsAdmin getAdminByUserName(String name) {
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdmin) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public List<UmsPermission> getPermissionList(long adminId) {
        return null;
    }
}
