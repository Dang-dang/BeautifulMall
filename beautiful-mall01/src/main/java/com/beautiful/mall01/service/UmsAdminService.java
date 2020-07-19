package com.beautiful.mall01.service;

import com.beautiful.mall01.mbg.model.UmsAdmin;
import com.beautiful.mall01.mbg.model.UmsPermission;

import java.util.List;

public interface UmsAdminService {

    UmsAdmin getAdminByUsername(String username);

    UmsAdmin register(UmsAdmin admin);

    String login(String username,String password);

    List<UmsPermission> getPermissionList(Long adminId);
}
