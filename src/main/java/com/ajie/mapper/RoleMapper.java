package com.ajie.mapper;

import com.ajie.entity.Premission;
import com.ajie.entity.Role;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 阿杰
 * @create 2022
 */
public interface RoleMapper {

    /**
     * 根据用户编号查询用户角色
     * 一个用户可以有多个角色
     * @param userId
     * @return
     */
    List<Role> findByUserId(Integer userId);

    /**
     * 查询角色信息
     * @param queryString
     * @return
     */
    Page<Role> findPage(String queryString);

    /**
     * 添加角色信息
     * @param role
     * @return
     */
    int insert(Role role);

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    int update(Role role);

    /**
     * 根据ID删除角色信息
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 查询权限列表
     * @return
     */
    List<Premission> findPermissions();

    /**
     * 根据角色ID添加权限信息
     * @param roleId
     * @param premissionId
     */
    void insertPremissions(@Param("roleId") Integer roleId, @Param("premissionId") Integer premissionId);

    /**
     * 根据角色ID添加权限信息
     * @param roleId
     * @param menuId
     */
    void insertMenus(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);



    /**
     * 根据角色ID删除权限信息
     * @param roleId
     */
    void deleteByRoleId(Integer roleId);

    /**
     * 根据角色ID删除菜单信息
     * @param id
     */
    void deleteByMenuId(Integer id);

    /**
     * 查询所有权限
     * @return
     */
    List<Role> findAll();
}
