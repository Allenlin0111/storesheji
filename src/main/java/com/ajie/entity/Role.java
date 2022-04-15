package com.ajie.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色信息表
 * @Author 阿杰
 * @create 2022
 */
@Data
public class Role {

    private Integer id;

    @ApiModelProperty("角色标识")
    private String name;

    @ApiModelProperty("角色描述")
    private String remark;

    @ApiModelProperty("菜单列表")
    private List<Menu> menus;

    @ApiModelProperty("角色权限")
    private List<Premission> premissions;
}
