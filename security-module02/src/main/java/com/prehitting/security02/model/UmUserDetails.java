package com.prehitting.security02.model;

import com.prehitting.db.model.UmUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @ClassName UmUserDetails
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/8 21:55
 * @Version 1.0
 */
public class UmUserDetails implements UserDetails {

    private UmUser user;


    private Set<SimpleGrantedAuthority> authorities;

    public UmUserDetails(UmUser user, Set<SimpleGrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus()==1;
    }
}
