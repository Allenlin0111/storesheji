package com.ajie.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 阿杰
 * @create 2022
 */
@Data
public class Premission {
    private Integer id;

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("权限标识")
    private String premission;
}
