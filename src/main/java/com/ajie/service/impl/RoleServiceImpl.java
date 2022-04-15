package com.ajie.service.impl;

import com.ajie.constant.MessageConstant;
import com.ajie.entity.Menu;
import com.ajie.entity.Premission;
import com.ajie.entity.Role;
import com.ajie.mapper.RoleMapper;
import com.ajie.result.PageResult;
import com.ajie.result.QueryInfo;
import com.ajie.result.Result;
import com.ajie.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ajie
 * @createTime 2022
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Result findPage(QueryInfo queryInfo) {
        try {
            PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
            Page<Role> page = roleMapper.findPage(queryInfo.getQueryString());
            return Result.success(MessageConstant.ROLE_SELECT_SUCCESS, new PageResult(page.getResult(), page.getTotal()));
        } catch (Exception e) {
            log.info("程序出错 ---> {}", e.getMessage());
            return Result.fail(MessageConstant.ROLE_SELECT_FAIL + e.getMessage());
        }
    }

    /**
     * 添加角色信息
     * @param role
     * @return
     */
    @Override
    public Result insert(Role role) {
        try {
            //先添加角色信息
            roleMapper.insert(role);
            if (role.getMenus().size() > 0) {
                for (Menu menu : role.getMenus()) {
                    roleMapper.insertMenus(role.getId(), menu.getId());
                }
            }
            //判断权限集合是否大于0
            if (role.getPremissions().size() > 0) {
                for (Premission premission : role.getPremissions()) {
                    roleMapper.insertPremissions(role.getId(), premission.getId());
                }
            }
            return Result.success(MessageConstant.ROLE_INSERT_SUCCESS);
        } catch (Exception e) {
            log.info("程序出错 ---> {}", e.getMessage());
            return Result.fail(MessageConstant.ROLE_INSERT_FAIL + e.getMessage());
        }
    }

    @Override
    public Result delete(Integer id) {
        try {
            roleMapper.delete(id);
            return Result.success(MessageConstant.ROLE_DELETE_SUCCESS);
        } catch (Exception e) {
            log.info("程序出错 ---> {}", e.getMessage());
            return Result.fail(MessageConstant.ROLE_DELETE_FAIL + e.getMessage());
        }
    }

    @Override
    public Result update(Role role) {
        try {
            //修改角色信息
            roleMapper.update(role);
            //删除原来的菜单列表
            roleMapper.deleteByMenuId(role.getId());
            //先删除原来的权限信息
            roleMapper.deleteByRoleId(role.getId());
            List<Menu> menus = role.getMenus();
            //添加菜单信息
            if (menus.size() > 0) {
                for (Menu menu : menus) {
                    roleMapper.insertMenus(role.getId(), menu.getId());
                }
            }
            List<Premission> premissions = role.getPremissions();
            if (premissions.size() > 1) {
                //再添加角色信息
                for (Premission premission : premissions) {
                    roleMapper.insertPremissions(role.getId(), premission.getId());
                }
            }
            return Result.success(MessageConstant.ROLE_UPDATE_SUCCESS);
        } catch (Exception e) {
            log.info("程序出错 ---> {}", e.getMessage());
            return Result.fail(MessageConstant.ROLE_UPDATE_FAIL + e.getMessage());
        }
    }


    /**
     * 查询所有权限
     * @return
     */
    @Override
    public Result findPermissions() {
        try {
            List<Premission> premissions = roleMapper.findPermissions();
            return Result.success(MessageConstant.PREMISSION_GET_SUCCESS, premissions);
        } catch (Exception e) {
            log.info("程序出错 ---> {}", e.getMessage());
            return Result.fail(MessageConstant.PREMISSION_GET_FAIL + e.getMessage());
        }
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }
}
