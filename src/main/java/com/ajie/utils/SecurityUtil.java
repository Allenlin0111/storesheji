package com.ajie.utils;

import com.ajie.entity.EasyUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author ajie
 * @createTime 2022
 */
public class SecurityUtil {

    /**
     * 获取用户名
     * @return
     */
    public static String getUsername() {
        try {
            return ((EasyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户ID
     * @return
     */
    public static Integer getUserId() {
        try {
            return ((EasyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户信息
     * @return
     */
    public static EasyUser getUserInfo() {
        try {
            return (EasyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }


}
