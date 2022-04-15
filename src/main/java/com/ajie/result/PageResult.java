package com.ajie.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据返回
 * @Author 阿杰
 * @create 2022
 */
@Data
@ApiModel(value = "分页响应数据")
public class PageResult implements Serializable {

    /**
     * 分页数据
     */
    @ApiModelProperty(value = "分页的数据")
    private List rows;

    /**
     * 数据总条数
     */
    @ApiModelProperty(value = "数据总条数")
    private long total;

    public PageResult(List rows, long total) {
        this.rows = rows;
        this.total = total;
    }
}
