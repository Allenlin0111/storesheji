package com.ajie.service;

import com.ajie.entity.Role;
import com.ajie.result.QueryInfo;
import com.ajie.result.Result;

import java.util.List;

/**
 * @author ajie
 * @createTime 2022
 */
public interface RoleService {
    /**
     * 分页查询角色信息
     * @param queryInfo
     * @return
     */
    Result findPage(QueryInfo queryInfo);

    /**
     * 添加角色
     * @param role
     * @return
     */
    Result insert(Role role);

    /**
     * 删除角色
     * @param id
     * @return
     */
    Result delete(Integer id);
    /**
     * 修改角色信息
     * @param role
     * @return
     */
    Result update(Role role);

    /**
     * 查询权限列表
     * @return
     */
    Result findPermissions();

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();
}
