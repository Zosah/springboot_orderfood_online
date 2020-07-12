package com.guzhz.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
@ApiModel(value="OrderDetail对象", description="")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单id")
    @TableId(value = "od_id", type = IdType.AUTO)
    private Integer odId;

    @ApiModelProperty(value = "订单号")
    private String odNo;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "od_createTime",fill = FieldFill.INSERT)
    private Date odCreateTime;

    @ApiModelProperty(value = "详细内容")
    private String odDetail;

    @ApiModelProperty(value = "姓名")
    private String odName;

    @ApiModelProperty(value = "手机")
    private String odPhone;

    @ApiModelProperty(value = "地址")
    private String odAddress;

    @ApiModelProperty(value = "成交价")
    private int odTotal;

    @ApiModelProperty(value = "备注")
    private String odRemarks;

    @ApiModelProperty(value = "订单状态")
    private Integer odStatus;

    @ApiModelProperty(value = "用户id")
    private Integer uId;

}
