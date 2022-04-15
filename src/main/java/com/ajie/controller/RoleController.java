package com.ajie.controller;

import com.ajie.entity.Role;
import com.ajie.result.QueryInfo;
import com.ajie.result.Result;
import com.ajie.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ajie
 * @createTime 2022
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "查询角色信息")
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryInfo queryInfo) {
        return roleService.findPage(queryInfo);
    }

    @ApiOperation(value = "添加角色信息")
    @PostMapping("/insert")
    public Result insert(@RequestBody Role role) {
        return roleService.insert(role);
    }

    @ApiOperation(value = "删除角色信息")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        return roleService.delete(id);
    }

    @ApiOperation(value = "修改角色信息")
    @PostMapping("/update")
    public Result update(@RequestBody Role role) {
        return roleService.update(role);
    }

    @ApiOperation(value = "查询所有的权限列表")
    @GetMapping("/findPermissions")
    public Result findPermissions() {
        return roleService.findPermissions();
    }
}
