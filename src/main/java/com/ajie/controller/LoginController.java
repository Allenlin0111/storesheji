package com.ajie.controller;

import com.ajie.constant.MessageConstant;
import com.ajie.entity.EasyUser;
import com.ajie.result.Result;
import com.ajie.service.UserService;
import com.ajie.utils.RedisUtil;
import com.ajie.utils.SecurityUtil;
import com.ajie.vo.LoginVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * @author ajie
 * @createTime 2022
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "post请求,参数为用户名username, 密码password", httpMethod = "POST")
    public Result login(@RequestBody LoginVo loginVo) {
        return userService.login(loginVo);
    }

    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/getLoginInfo")
    public Result getLoginInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        String username = principal.getName();
        EasyUser user = userService.findByusername(username);
        user.setPassword(null);
        return new Result(true, MessageConstant.USER_SELECT_SUCCESS, user);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "退出", httpMethod = "GET")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        Integer userId = SecurityUtil.getUserId();
        //删除菜单信息
        redisUtil.delKey("menu_" + userId);
        //清除spring security用户认证信息
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new Result(true, MessageConstant.LOGOUT_SUCCESS);
    }
}

//    @GetMapping("/sendSms")
//    @ApiOperation("发送验证码")
//    public Result sendSms(String phoneNumber) {
//        //得到验证码
//        String code = SmsUtil.createCode();
//        try {
//            //发送验证码
//            SmsUtil.sendSms(phoneNumber, code);
//            //以秒为单位，5分钟后过期
//            redisUtil.setObjectTime("codeSms", code, 60*5);
//            return Result.success("验证码发送成功");
//        } catch (Exception e) {
//            return Result.fail("验证码发送失败");
//        }
//    }
//
//    @ApiOperation("微信登录")
//    @PostMapping("/wxlogin")
//    public Result wxlogin(@RequestBody WxLoginVo wxLoginVo) {
//        return userService.wxlogin(wxLoginVo);
//    }
//
//    @GetMapping("/runLogin")
//    public Result runLogin(String code) {
//        return userService.runLogin(code);
//    }
//}
