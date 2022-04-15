package com.ajie.service;

import com.ajie.entity.EasyUser;
import com.ajie.entity.Menu;
import com.ajie.entity.Role;
import com.ajie.result.QueryInfo;
import com.ajie.result.Result;
import com.ajie.vo.LoginVo;
import com.ajie.vo.UpdateVo;

import java.util.List;
import java.util.Map;

/**
 * @Author 阿杰
 * @create 2022
 */
public interface UserService {
    /**
     * 分页查询
     * @param queryInfo 查询条件
     * @return
     */
    Result findPage(QueryInfo queryInfo);

    /**
     * 更改用户状态
     * @param updateVo
     * @return
     */
    Result updateState(UpdateVo updateVo);

    /**
     * 重置密码
     * @param updateVo
     * @return
     */
    Result updatePassword(UpdateVo updateVo);

    /**
     * 添加用户信息
     * @param easyUser
     * @return
     */
    Result add(EasyUser easyUser);

    /**
     * 修改用户信息
     * @param easyUser
     * @return
     */
    Result edit(EasyUser easyUser);

    /**
     * 根据用户名获取用户对象
     * @param username
     * @return
     */
    EasyUser findByusername(String username);

    /**
     * 根据用户电话获取用户对象
     * @param phoneNumber
     * @return
     */
    EasyUser findByPhone(String phoneNumber);

    /**
     * 登录
     * @param loginVo
     * @return
     */
    Result login(LoginVo loginVo);

    /**
     * 微信登录
     * @param wxLoginVo
     * @return
     */
//    Result wxlogin(WxLoginVo wxLoginVo);
//
//    /**
//     * 根据用户Id查询菜单列表
//     * @return
//     */
    List<Menu> findMenuByUserId();

    /**
     * 根据用户ID查询角色
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(Integer userId);

    /**
     * 根据时间获取微信注册用户
     * @param beginTime
     * @param endTime
     * @return
     */
    List<Map<String, Object>> findByTime(String beginTime, String endTime);

//    /**
//     *
//     * @param code
//     * @return
//     */
//    Result runLogin(String code);
}
