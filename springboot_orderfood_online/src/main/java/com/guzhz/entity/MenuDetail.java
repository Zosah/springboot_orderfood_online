package com.guzhz.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MenuDetail对象", description="")
@TableName("menu_detail")
public class MenuDetail implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "菜品id")
    @TableId(value = "md_id", type = IdType.AUTO)
    private Integer mdId;

    @ApiModelProperty(value = "菜品名称")
    private String mdName;

    @ApiModelProperty(value = "菜品价格")
    private Integer mdPrice;

    @ApiModelProperty(value = "菜品数量")
    private Integer mdAmount;

    @ApiModelProperty(value = "菜品图片")
    private String mdUrl;

    @ApiModelProperty(value = "是否为新产品：默认为是1，否0")
    private String mdNew;

    @ApiModelProperty(value = "推荐星值，默认为3； 0-5")
    private String mdStar;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer mdDeleted;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer mdVersion;

    @ApiModelProperty(value = "关联MenuType")
    private Integer mtId;

    @TableField(exist = false)  //重点:Mybatis-plus中默认操作忽略该字段
    private MenuType menuType;

}
