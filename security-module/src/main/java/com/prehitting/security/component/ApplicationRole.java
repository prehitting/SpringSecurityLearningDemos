package com.prehitting.security.component;


import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationRole {

    ADMIN("admin", Sets.newHashSet(ApplicationPermission.USER_READ_ALL)),
    USER("user",Sets.newHashSet(ApplicationPermission.USER_DELETE));

    @Getter
    private final String roleName;

    @Getter
    private final Set<ApplicationPermission> permissions;

    ApplicationRole(String roleName, Set<ApplicationPermission> permissions) {
        this.roleName = roleName;
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = this.permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+roleName.toUpperCase()));
        return permissions;
    }
}
