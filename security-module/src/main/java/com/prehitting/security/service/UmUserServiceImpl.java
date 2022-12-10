package com.prehitting.security.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.prehitting.db.mapper.UmUserMapper;
import com.prehitting.db.model.UmPermission;
import com.prehitting.db.model.UmUser;
import com.prehitting.db.service.UmUserService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/7 19:09
 * @Version 1.0
 */
@Service
public class UmUserServiceImpl extends ServiceImpl<UmUserMapper, UmUser> implements UmUserService{

    @Override
    public UmUser getUserByUid(Long uid) {
        return null;
    }

    @Override
    public UmUser getUserByUsername(String username) {
        return null;
    }

    @Override
    public Set<UmPermission> getAllPermissions(Long uid) {
        return null;
    }
}
