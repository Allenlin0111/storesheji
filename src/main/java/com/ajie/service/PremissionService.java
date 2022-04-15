package com.ajie.service;

import com.ajie.entity.Premission;
import com.ajie.result.QueryInfo;
import com.ajie.result.Result;

/**
 * @author ajie
 * @createTime 2022
 */
public interface PremissionService {
    /**
     * 分页查询角色信息
     * @param queryInfo
     * @return
     */
    Result findPage(QueryInfo queryInfo);

    /**
     * 添加角色
     * @param premission
     * @return
     */
    Result insert(Premission premission);

    /**
     * 删除角色
     * @param id
     * @return
     */
    Result delete(Integer id);
    /**
     * 修改角色信息
     * @param premission
     * @return
     */
    Result update(Premission premission);
}
