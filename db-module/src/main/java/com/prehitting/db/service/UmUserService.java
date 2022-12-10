package com.prehitting.db.service;

import com.prehitting.db.model.UmPermission;
import com.prehitting.db.model.UmUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YiMing
 * @since 2022-12-08
 */
public interface UmUserService extends IService<UmUser> {

    UmUser getUserByUid(Long uid);

    UmUser getUserByUsername(String username);

    Set<UmPermission> getAllPermissions(Long uid);
}
