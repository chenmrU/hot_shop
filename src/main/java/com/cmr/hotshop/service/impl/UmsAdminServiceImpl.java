package com.cmr.hotshop.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cmr.hotshop.common.utils.JwtUtil;
import com.cmr.hotshop.dao.UmsAdminMapper;
import com.cmr.hotshop.dao.UmsAdminPermissionRelationMapper;
import com.cmr.hotshop.dao.UmsPermissionMapper;
import com.cmr.hotshop.entity.*;
import com.cmr.hotshop.service.UmsAdminService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenmengrui
 * @Description: 后台管理员Service
 * @date 2019/12/26 19:53
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private UmsAdminPermissionRelationMapper umsAdminPermissionRelationMapper;

    @Resource
    private UmsPermissionMapper umsPermissionMapper;

    @Override
    public UmsAdmin getAdminByUserName(String name) {
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andUsernameEqualTo(name);
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(umsAdminExample);
        if (CollectionUtil.isNotEmpty(umsAdmins)) {
            return umsAdmins.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdminParam) {
        umsAdminParam.setCreateTime(DateUtil.date());
        umsAdminParam.setStatus(1);

        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtil.copyProperties(umsAdminParam, umsAdmin);
        //查询用户名是否冲突
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(umsAdminExample);
        if (CollectionUtil.isNotEmpty(umsAdmins)) {
            return null;
        }
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        umsAdminMapper.insert(umsAdmin);

        return umsAdminParam;
    }

    @Override
    public String login(String username, String password) {
        String token;
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (ObjectUtil.isNull(userDetails) || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("账号或密码错误");
        }
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token = jwtUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public List<UmsPermission> getPermissionList(long adminId) {
        List<UmsPermission> umsPermissions = CollectionUtil.newArrayList();
        UmsAdminPermissionRelationExample umsAdminPermissionRelationExample = new UmsAdminPermissionRelationExample();
        umsAdminPermissionRelationExample.createCriteria().andAdminIdEqualTo(adminId);
        List<UmsAdminPermissionRelation> umsAdminPermissionRelations =
                umsAdminPermissionRelationMapper.selectByExample(umsAdminPermissionRelationExample);
        if (CollectionUtil.isNotEmpty(umsAdminPermissionRelations)) {
            List<Long> umsPermissionsIds = umsAdminPermissionRelations.stream()
                    .map(UmsAdminPermissionRelation::getPermissionId)
                    .collect(Collectors.toList());
            UmsPermissionExample umsPermissionExample = new UmsPermissionExample();
            umsPermissionExample.createCriteria().andIdIn(umsPermissionsIds);
            umsPermissions = umsPermissionMapper.selectByExample(umsPermissionExample);
        }
        return umsPermissions;
    }
}
