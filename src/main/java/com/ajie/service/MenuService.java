package com.ajie.service;

import com.ajie.entity.Menu;
import com.ajie.result.QueryInfo;
import com.ajie.result.Result;

import java.util.List;

/**
 * @Author 阿杰
 * @create 2022
 */
public interface MenuService {

    /**
     * 根据用户权限查询对应的菜单
     * @return
     */
    List<Menu> findMenuByUserId();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> findMenuByRole();

    /**
     * 分页查询菜单信息
     * @param queryInfo
     * @return
     */
    Result findPage(QueryInfo queryInfo);

    /**
     * 添加菜单信息
     * @param menu
     */
    Result insert(Menu menu);

    /**
     * 删除菜单
     * @param id
     */
    Result delete(Integer id);

    /**
     * 删除菜单信息
     * @param menu
     */
    Result update(Menu menu);
}
