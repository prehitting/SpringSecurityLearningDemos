package com.prehitting.security.component;

import lombok.Getter;

public enum ApplicationPermission {

    USER_READ_ALL("user:all"),
    USER_DELETE("user:delete");

    @Getter
    private final String permission;

    ApplicationPermission(String permission) {
        this.permission = permission;
    }

}
