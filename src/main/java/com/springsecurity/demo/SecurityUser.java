package com.springsecurity.demo;

import com.springsecurity.demo.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @program: spring-security
 * @description:
 * @author: DrongRonglong
 * @create: 2019-03-11 16:03
 **/

public class SecurityUser extends UserEntity implements UserDetails {

    public SecurityUser(UserEntity user) {
        if (user != null) {
            this.setUser_uuid(user.getUser_uuid());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setEmail(user.getEmail());
            this.setTelephone(user.getTelephone());
            this.setRole(user.getRole());
            this.setImage(user.getImage());
            this.setLast_ip(user.getLast_ip());
            this.setLast_time(user.getLast_time());
            this.setId(user.getId());
            this.setRealname(user.getRealname());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String username = this.getUsername();
        if (username != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(username);
            authorities.add(authority);
        }
        return authorities;
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
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
