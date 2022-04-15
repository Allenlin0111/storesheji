package com.ajie.controller;

import com.ajie.entity.EasyUser;
import com.ajie.entity.Role;
import com.ajie.result.QueryInfo;
import com.ajie.result.Result;
import com.ajie.service.RoleService;
import com.ajie.service.UserService;
import com.ajie.vo.UpdateVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 阿杰
 * @create 2022
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @PostMapping("/findPage")
    public Result findAll(@RequestBody QueryInfo queryInfo) {
        return userService.findPage(queryInfo);
    }

    @ApiOperation("查询所有的角色")
    @GetMapping("/findRole")
    public List<Role> findRole() {
        return roleService.findAll();
    }

    @PostMapping("/updateState")
    public Result updateState(@RequestBody UpdateVo updateVo) {
        return userService.updateState(updateVo);
    }

    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody UpdateVo updateVo) {
        return userService.updatePassword(updateVo);
    }

    @PostMapping("/add")
    public Result add(@RequestBody EasyUser easyUser) {
        return userService.add(easyUser);
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody EasyUser easyUser) {
        return userService.edit(easyUser);
    }

}
