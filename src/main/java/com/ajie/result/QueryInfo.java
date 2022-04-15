package com.ajie.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页参数
 * @Author 阿杰
 * @create 2022
 */
@Data
@ApiModel(value = "分页参数")
public class QueryInfo implements Serializable {
    /**
     * 页码
     */
    @ApiModelProperty(value = "页数")
    private Integer pageNumber;

    /**
     * 页数
     */
    @ApiModelProperty(value = "页码大小")
    private Integer pageSize;

    /**
     * 查询条件
     */
    @ApiModelProperty(value = "搜索框输入的查询关键词")
    private String queryString;
}
