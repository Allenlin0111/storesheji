package com.ajie.service.impl;

import com.ajie.constant.MessageConstant;
import com.ajie.entity.Menu;
import com.ajie.mapper.MenuMapper;
import com.ajie.result.PageResult;
import com.ajie.result.QueryInfo;
import com.ajie.result.Result;
import com.ajie.service.MenuService;
import com.ajie.utils.SecurityUtil;
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
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findMenuByUserId() {
        Integer userId = SecurityUtil.getUserId();
        //如果为空从数据库获取
        return menuMapper.findByUserId(userId);
    }

    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List<Menu> findMenuByRole() {
        return menuMapper.findMenuByRole();
    }

    @Override
    public Result findPage(QueryInfo queryInfo) {
        try {
            PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
            Page<Menu> page = menuMapper.findPage(queryInfo.getQueryString());
            return Result.success(MessageConstant.MENU_SELECT_SUCCESS, new PageResult(page.getResult(), page.getTotal()));
        } catch (Exception e) {
            log.info("程序异常 --> {}", e.getMessage());
            return Result.fail(MessageConstant.MENU_SELECT_FAIL + e.getMessage());
        }
    }

    /**
     * 添加默认超级管理员拥有菜单权限
     * @param menu
     * @return
     */
    @Override
    public Result insert(Menu menu) {
        try {
            menuMapper.insert(menu);
            menuMapper.insertAdmin(menu.getId());
            return Result.success(MessageConstant.MENU_INSERT_SUCCESS);
        } catch (Exception e) {
            log.info("程序异常 --> {}", e.getMessage());
            return Result.fail(MessageConstant.MENU_INSERT_FAIL + e.getMessage());
        }
    }

    @Override
    public Result delete(Integer id) {
        try {
            menuMapper.delete(id);
            return Result.success(MessageConstant.MENU_DELETE_SUCCESS);
        } catch (Exception e) {
            log.info("程序异常 --> {}", e.getMessage());
            return Result.fail(MessageConstant.MENU_DELETE_FAIL + e.getMessage());
        }
    }

    @Override
    public Result update(Menu menu) {
        try {
            menuMapper.update(menu);
            return Result.success(MessageConstant.MENU_UPDATE_SUCCESS);
        } catch (Exception e) {
            log.info("程序异常 --> {}", e.getMessage());
            return Result.fail(MessageConstant.MENU_UPDATE_FAIL + e.getMessage());
        }
    }
}
