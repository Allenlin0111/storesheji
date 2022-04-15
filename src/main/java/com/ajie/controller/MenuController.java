package com.ajie.controller;

import com.ajie.entity.Menu;
import com.ajie.result.QueryInfo;
import com.ajie.result.Result;
import com.ajie.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ajie
 * @createTime 2022
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "根据用户Id查询菜单列表")
    @GetMapping("/findMenu")
    public List<Menu> findMenu() {
        return menuService.findMenuByUserId();
    }

    @ApiOperation(value = "分页查询菜单信息")
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryInfo queryInfo) {
        return menuService.findPage(queryInfo);
    }

    @ApiOperation(value = "添加菜单信息")
    @PostMapping("/insert")
    public Result insert(@RequestBody Menu menu) {
        return menuService.insert(menu);
    }

    @ApiOperation(value = "删除菜单信息")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        return menuService.delete(id);
    }
    @ApiOperation(value = "修改菜单信息")
    @PostMapping("/update")
    public Result update(@RequestBody Menu menu) {
        return menuService.update(menu);
    }


}
