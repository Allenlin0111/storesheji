package com.ajie.mapper;

import com.ajie.entity.EasyUser;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author 阿杰
 * @create 2022
 */
public interface UserMapper {
    /**
     * 数据查询
     * @param queryString 根据字符串查询
     * @return
     */
    Page<EasyUser> findPage(String queryString);

    /**
     * 根据用户名查询数据
     * @param username
     * @return
     */
    EasyUser findUserByName(String username);

    /**
     * 根据用户名查询数据
     * @param phoneNumber
     * @return
     */
    EasyUser findUserByPhone(String phoneNumber);

    /**
     * 修改用户信息
     * @param easyUser 用户参数
     * @return
     */
    void update(EasyUser easyUser);

    /**
     * 根据用户编号查询用户信息
     * @param userId
     * @return
     */
    EasyUser findById(Integer userId);

    /**
     * 添加用户
     * @param easyUser
     * @return
     */
    void insert(EasyUser easyUser);

    /**
     * 根据角色ID查询用户信息
     * @param roleId
     * @return
     */
    List<EasyUser> findByRoleId(Integer roleId);

    /**
     * 根据权限ID查询对应的用户信息
     * @param premissionId
     * @return
     */
    List<EasyUser> findByPremissionId(Integer premissionId);

    /**
     * 根据时间查询会员数量
     * @param beginTime
     * @param endTime
     * @return
     */
    List<Map<String, Object>> findByTime(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 添加用户角色
     * @param userId
     * @param roleId
     */
    void insertRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    /**
     * 删除用户角色
     * @param userId
     */
    void deleteRole(@Param("userId") Integer userId);
}
