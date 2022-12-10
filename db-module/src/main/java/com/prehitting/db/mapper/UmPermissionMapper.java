package com.prehitting.db.mapper;

import com.prehitting.db.model.UmPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YiMing
 * @since 2022-12-08
 */
@Mapper
public interface UmPermissionMapper extends BaseMapper<UmPermission> {

    Set<UmPermission> getAllPermissionsByUid(@Param("uid") Long uid);
}
