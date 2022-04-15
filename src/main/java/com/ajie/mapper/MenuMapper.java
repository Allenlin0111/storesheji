package com.ajie.mapper;

import com.ajie.entity.Menu;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 阿杰
 * @create 2022
 */
public interface MenuMapper {
    /**
     * 根据用户编号查询对应的菜单信息
     * @param userId 用户ID
     * @return
     */
    List<Menu> findByUserId(Integer userId);

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> findMenuByRole();

    /**
     * 分页查询菜单信息
     * @param queryString
     * @return
     */
    Page<Menu> findPage(String queryString);

    /**
     * 添加菜单信息
     * @param menu
     */
    void insert(Menu menu);

    /**
     * 删除菜单
     * @param id
     */
    void delete(Integer id);

    /**
     * 删除菜单信息
     * @param menu
     */
    void update(Menu menu);

    /**
     * 添加权限
     * @param menuId
     */
    void insertAdmin(@Param("menuId") Integer menuId);
}
