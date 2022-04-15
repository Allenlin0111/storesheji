package com.ajie.service.impl;

import com.ajie.constant.MessageConstant;
import com.ajie.entity.EasyUser;
import com.ajie.entity.Menu;
import com.ajie.entity.Role;
import com.ajie.mapper.MenuMapper;
import com.ajie.mapper.RoleMapper;
import com.ajie.mapper.UserMapper;
import com.ajie.result.PageResult;
import com.ajie.result.QueryInfo;
import com.ajie.result.Result;
import com.ajie.service.UserService;
import com.ajie.utils.HttpUtil;
import com.ajie.utils.JwtTokenUtil;
import com.ajie.utils.RedisUtil;
import com.ajie.utils.SecurityUtil;
import com.ajie.vo.LoginVo;
import com.ajie.vo.UpdateVo;
//import com.ajie.vo.WxLoginVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 阿杰
 * @create 2022
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    @Override
    public Result findPage(QueryInfo queryInfo) {
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        Page<EasyUser> page = userMapper.findPage(queryInfo.getQueryString());
        if (page == null) {
            return new Result(false, MessageConstant.USER_SELECT_FAIL);
        }
        long total = page.getTotal();
        List<EasyUser> result = page.getResult();
        return new Result(true, MessageConstant.USER_SELECT_SUCCESS, new PageResult(result, total));
    }

    @Override
    public Result updateState(UpdateVo updateVo) {
        EasyUser easyUser = this.getUser(updateVo.getAdminId());
        if (easyUser == null) {
            return new Result(false, MessageConstant.ADMIN_NOT_LOGIN, 403);
        }
        EasyUser byId = this.getUser(updateVo.getUserId());
        if (byId.isState()) {
            byId.setState(false);
        } else {
            byId.setState(true);
        }
        userMapper.update(byId);
        return new Result(true, MessageConstant.UPDATE_USER_SUCCESS);
    }

    @Override
    public Result updatePassword(UpdateVo updateVo) {
        EasyUser easyUser = this.getUser(updateVo.getAdminId());
        if (easyUser == null) {
            return new Result(false, MessageConstant.ADMIN_NOT_LOGIN, 403);
        }
        EasyUser byId = this.getUser(updateVo.getUserId());
        byId.setPassword(passwordEncoder.encode("123456"));
        userMapper.update(byId);
        return new Result(true, MessageConstant.UPDATE_USER_SUCCESS);
    }

    private EasyUser getUser(Integer userId) {
        return userMapper.findById(userId);
    }

    @Override
    public Result add(EasyUser easyUser) {
        EasyUser easyUserByName = userMapper.findUserByName(easyUser.getUsername());
        if (easyUserByName != null) {
            return new Result(false, MessageConstant.USER_EXIST);
        }
        easyUser.setPassword(passwordEncoder.encode(easyUser.getPassword()));
        userMapper.insert(easyUser);
        List<Role> roles = easyUser.getRoles();
        if (roles.size() > 0) {
            for (Role role : roles) {
                userMapper.insertRole(easyUser.getId(), role.getId());
            }
        }
        return new Result(true, MessageConstant.ADD_USER_SUCCESS);
    }

    @Override
    public Result edit(EasyUser easyUser) {
        userMapper.deleteRole(easyUser.getId());
        List<Role> roles = easyUser.getRoles();
        if (roles.size() > 0) {
            for (Role role : roles) {
                userMapper.insertRole(easyUser.getId(), role.getId());
            }
        }
        userMapper.update(easyUser);
        return new Result(true, MessageConstant.UPDATE_USER_SUCCESS);
    }

    @Override
    public EasyUser findByusername(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public Result login(LoginVo loginVo) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginVo.getUsername());
        if (null == userDetails || !passwordEncoder.matches(loginVo.getPassword(), userDetails.getPassword())) {
            return Result.fail("用户名或密码错误！");
        }
        if (!userDetails.isEnabled()) {
            return Result.fail("该帐号已被禁用，请联系管理员！");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> map = new HashMap<>(16);
        map.put("tokenHead", tokenHead);
        map.put("token", token);
        return new Result(true, MessageConstant.LOGIN_SUCCESS, map);
    }

    @Override
    public List<Menu> findMenuByUserId() {
        Integer userId = SecurityUtil.getUserId();
        return menuMapper.findByUserId(userId);
    }

    /**
     * 根据用户ID查询角色
     * @param userId
     * @return
     */
    @Override
    public List<Role> findRolesByUserId(Integer userId) {
        return roleMapper.findByUserId(userId);
    }

    @Override
    public List<Map<String, Object>> findByTime(String beginTime, String endTime) {
        return userMapper.findByTime(beginTime, endTime);
    }

    @Override
    public EasyUser findByPhone(String phoneNumber) {
        return userMapper.findUserByPhone(phoneNumber);
    }

//    @Override
//    public Result wxlogin(WxLoginVo wxLoginVo) {
//        //1. 校验验证码输入是否正确
//        //判断验证码是否存在
//        if (!redisUtil.hasKey("codeSms")) {
//            return Result.fail("验证码已过期");
//        }
//        //判断验证码是正确
//        String codeSms = redisUtil.getObject("codeSms").toString();
//        if (!codeSms.equals(wxLoginVo.getMsgCode())) {
//            return Result.fail("验证码错误");
//        }
//        //访问微信url
//        String url = "https://api.weixin.qq.com/sns/jscode2session";
//        //添加参数
//        Map<String, String> map = new HashMap<>(16);
//        map.put("appid", appid);
//        map.put("secret", secret);
//        map.put("js_code", wxLoginVo.getCode());
//        map.put("grant_type", "authorization_code");
//        try {
//            //2. 根据请求码获取用户openId
//            String response = HttpUtil.getResponse(url, map);
//            //将字符串转为Json
//            JSONObject object = JSON.parseObject(response);
//            //得到openId
//            String openId = object.getString("openid");
//            //得到sessionKey
//            String sessionKey = object.getString("session_key");
//            //3. 根据用户输入的电话号码去数据库查询是否有该用户 不存在就添加 存在就修改
//            EasyUser user = userMapper.findUserByPhone(wxLoginVo.getPhoneNumber());
//            UserDetails userDetails;
//            if (null != user) {
//                userDetails = userDetailsService.loadUserByUsername(user.getUsername());
//                user.setAddress(wxLoginVo.getUserInfo().getAddress());
//                user.setAvatarUrl(wxLoginVo.getUserInfo().getAvatarUrl());
//                user.setNickName(wxLoginVo.getUserInfo().getNickName());
//                user.setSex(wxLoginVo.getUserInfo().getSex());
//                user.setOpenId(openId);
//                userMapper.update(user);
//            } else {
//                EasyUser userInfo = wxLoginVo.getUserInfo();
//                userInfo.setPhoneNumber(wxLoginVo.getPhoneNumber());
//                userInfo.setUsername(wxLoginVo.getPhoneNumber());
//                userInfo.setOpenId(openId);
//                userMapper.insert(wxLoginVo.getUserInfo());
//                userDetails = userDetailsService.loadUserByUsername(userInfo.getPhoneNumber());
//            }
//            //更新security登录用户对象
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            String token = jwtTokenUtil.generateToken(userDetails);
//            Map<String, String> result = new HashMap<>(16);
//            result.put("openId", openId);
//            result.put("sessionKey", sessionKey);
//            result.put("tokenHead", tokenHead);
//            result.put("token", token);
//            //4. 返回用户信息以及微信所需参数 token
//            return Result.success(MessageConstant.LOGIN_SUCCESS, result);
//        } catch (Exception e) {
//            log.info("程序出错： --> {}", e.getMessage());
//            return Result.fail("登录失败");
//        }
//    }

//    @Override
//    public Result runLogin(String code) {
//        //访问微信url
//        String url = "https://api.weixin.qq.com/sns/jscode2session";
//        //添加参数
//        Map<String, String> map = new HashMap<>(16);
//        map.put("appid", appid);
//        map.put("secret", secret);
//        map.put("js_code", code);
//        map.put("grant_type", "authorization_code");
//        try {
//            //2. 根据请求码获取用户openId
//            String response = HttpUtil.getResponse(url, map);
//            //将字符串转为Json
//            JSONObject object = JSON.parseObject(response);
//            //得到sessionKey
//            String sessionKey = object.getString("session_key");
//            return Result.success(MessageConstant.LOGIN_SUCCESS, sessionKey);
//        } catch (Exception e) {
//            log.info("程序出错： --> {}", e.getMessage());
//            return Result.fail("登录失败");
//        }
//    }
}
