package com.prehitting.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("um_user_role_relation")
@ApiModel(value = "UmUserRoleRelation对象", description = "")
public class UmUserRoleRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("relation id")
    @TableId(value = "relation_id", type = IdType.AUTO)
    private Long relationId;

    @ApiModelProperty("key")
    @TableField("uid")
    private Long uid;

    @ApiModelProperty("role id")
    @TableField("role_id")
    private Integer roleId;
}
