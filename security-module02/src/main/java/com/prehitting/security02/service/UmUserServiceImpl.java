package com.prehitting.security02.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import com.prehitting.db.mapper.UmPermissionMapper;
import com.prehitting.db.mapper.UmUserMapper;
import com.prehitting.db.model.UmPermission;
import com.prehitting.db.model.UmUser;
import com.prehitting.db.service.UmUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Set;

/**
 * @ClassName UmUserServiceImpl
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/8 22:03
 * @Version 1.0
 */
@Service
public class UmUserServiceImpl extends ServiceImpl<UmUserMapper, UmUser> implements UmUserService{

    @Resource
    private UmPermissionMapper umPermissionMapper;

    @Resource
    private UmUserMapper umUserMapper;

    @Override
    public UmUser getUserByUid(Long id) {
        return Optional.ofNullable(umUserMapper.selectById(id)).orElse(new UmUser());
    }

    @Override
    public UmUser getUserByUsername(String username) {
        LambdaQueryWrapper<UmUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmUser::getUsername, username);
        return Optional.ofNullable(umUserMapper.selectOne(wrapper)).orElse(new UmUser());
    }

    @Override
    public Set<UmPermission> getAllPermissions(Long uid) {
        Set<UmPermission> allPermissions = umPermissionMapper.getAllPermissionsByUid(uid);
        return Optional.ofNullable(allPermissions).orElse(Sets.newHashSet());
    }
}
