package com.ajie.mapper;

import com.ajie.entity.Premission;
import com.github.pagehelper.Page;

/**
 * @author ajie
 * @createTime 2022
 */
public interface PremissionMapper {
    /**
     * 分页查询角色信息
     * @param queryString
     * @return
     */
    Page<Premission> findPage(String queryString);

    /**
     * 添加角色
     * @param premissions
     * @return
     */
    void insert(Premission premissions);

    /**
     * 删除角色
     * @param id
     * @return
     */
    void delete(Integer id);
    /**
     * 修改角色信息
     * @param premissions
     * @return
     */
    void update(Premission premissions);

    /**
     * 添加默认给超级管理员添加权限
     * @param premissionId
     */
    void insertPremission(Integer premissionId);
}
