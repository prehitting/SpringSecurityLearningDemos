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
@TableName("um_permission")
@ApiModel(value = "UmPermission对象", description = "")
public class UmPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("permission id")
    @TableId("permission_id")
    private Integer permissionId;

    @ApiModelProperty("permission name")
    @TableField("permission_name")
    private String permissionName;

    @ApiModelProperty("enabled=1,else=0")
    @TableField("status")
    private Byte status;
}
