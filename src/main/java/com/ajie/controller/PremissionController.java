package com.ajie.controller;

import com.ajie.entity.Premission;
import com.ajie.result.QueryInfo;
import com.ajie.result.Result;
import com.ajie.service.PremissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ajie
 * @createTime 2022
 */
@RestController
@RequestMapping("/system/premission")
public class PremissionController {
    @Autowired
    private PremissionService premissionService;

    @ApiOperation(value = "查询角色信息")
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryInfo queryInfo) {
        return premissionService.findPage(queryInfo);
    }

    @ApiOperation(value = "添加角色信息")
    @PostMapping("/insert")
    public Result insert(@RequestBody Premission premission) {
        return premissionService.insert(premission);
    }

    @ApiOperation(value = "删除角色信息")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        return premissionService.delete(id);
    }

    @ApiOperation(value = "修改角色信息")
    @PostMapping("/update")
    public Result update(@RequestBody Premission premission) {
        return premissionService.update(premission);
    }
}
