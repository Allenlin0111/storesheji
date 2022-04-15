package com.ajie.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜单
 * @Author 阿杰
 * @create 2022
 */
@Data
public class Menu {
    private int id;

    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "对应后端路径")
    private String url;

    @ApiModelProperty(value = "路径")
    private String path;
    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;
    /**
     * 组件名
     */
    @ApiModelProperty(value = "组件名")
    private String component;
    /**
     * 是否需要权限
     */
    @ApiModelProperty(value = "是否需要权限")
    private boolean requireAuth;

    @ApiModelProperty(value = "父级菜单ID")
    @JSONField(serialize = false)
    private Integer parentId;
    /**
     * 子菜单
     */
    @ApiModelProperty(value = "子菜单")
    private List<Menu> children;
}
