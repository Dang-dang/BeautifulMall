package com.beautiful.mall01.service.impl;

import com.beautiful.mall01.mbg.model.UmsAdmin;
import com.beautiful.mall01.mbg.model.UmsPermission;
import com.beautiful.mall01.service.UmsAdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdmin admin) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return null;
    }
}
