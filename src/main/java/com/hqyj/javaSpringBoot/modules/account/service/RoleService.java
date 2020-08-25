package com.hqyj.javaSpringBoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.javaSpringBoot.modules.account.entity.Role;
import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();

    Result<Role> editRole(Role role);

    Result<Role> deleteRole(int roleId);

    PageInfo<Role> getRoles(SearchVo searchVo);

    List<Role> getRolesByUserId(int userId);

    List<Role> getRolesByResourceId(int resourceId);

    Role getRoleById(int roleId);
}
