package com.bookstore.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bookstore.repository.IUserRepository;


public class CustomUserDetail implements UserDetails {
    private final User user;
    private final IUserRepository userRepository;

    public CustomUserDetail(User user, IUserRepository userRepository){
        this.user = user;
        this.userRepository = userRepository;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of();
    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

    @Override
    public String getUsername(){
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired(){
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked(){
        return UserDetails.super.isAccountNonLocked();
    }
}
