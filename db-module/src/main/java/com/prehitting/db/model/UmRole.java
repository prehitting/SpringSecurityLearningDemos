package com.prehitting.db.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author YiMing
 * @since 2022-12-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("um_role")
@ApiModel(value = "UmRole对象", description = "")
@ToString
public class UmRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("role id")
    @TableId("role_id")
    private Integer roleId;

    @ApiModelProperty("role name")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty("enabled=1.else=0")
    @TableField("status")
    private Byte status;
}
