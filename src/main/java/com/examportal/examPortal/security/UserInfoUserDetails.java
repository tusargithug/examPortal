package com.examportal.examPortal.security;

import com.examportal.examPortal.Enum.Role;
import com.examportal.examPortal.Model.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserInfoUserDetails implements UserDetails {
    private final String userName;
    private final String password;
    private final Role grantedAuthorities;


    public UserInfoUserDetails(AppUser appUser) {
        userName = appUser.getUserName();
        password = appUser.getPassword();
        grantedAuthorities = appUser.getRoleType();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
    ////
    /////////////
    /////////////////////
    /////////////////////////
}
