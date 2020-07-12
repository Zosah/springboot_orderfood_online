package com.guzhz.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MenuType对象", description="MenuType对象")
@TableName("menu_type")
public class MenuType implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "类型id")
    @TableId(value = "mt_id", type = IdType.AUTO)
    private Integer mtId;

    @ApiModelProperty(value = "类型名")
    private String mtName;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer mtDeleted;

    @ApiModelProperty(value = "版本")
    @Version
    private Integer mtVersion;

}
