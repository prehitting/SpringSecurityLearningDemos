package com.prehitting.db.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@TableName("um_user")
@ApiModel(value = "UmUser对象", description = "")
public class UmUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("user id")
    @TableId("uid")
    private Long uid;

    @ApiModelProperty("username")
    @TableField("username")
    private String username;

    @ApiModelProperty("password")
    @TableField("password")
    private String password;

    @ApiModelProperty("enabled=1,else=0")
    @TableField("status")
    private Byte status;
}
