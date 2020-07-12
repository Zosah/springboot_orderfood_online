package com.guzhz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author Guzhz
 * @since 2020-06-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ShoppingCart对象", description="")
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "购物车序号")
    @TableId(value = "sc_id", type = IdType.AUTO)
    private Integer scId;

    @ApiModelProperty(value = "菜品名称")
    private String scName;

    @ApiModelProperty(value = "单品数量")
    private Integer scAmount;

    @ApiModelProperty(value = "菜品价格")
    private Integer scPrice;

    @ApiModelProperty(value = "菜品图片")
    private String scImg;

    @ApiModelProperty(value = "菜品id")
    private Integer mdId;

    @ApiModelProperty(value = "当前用户id")
    private Integer uId;

}
