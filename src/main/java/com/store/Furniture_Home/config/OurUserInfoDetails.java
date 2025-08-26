package com.store.Furniture_Home.config;

import com.store.Furniture_Home.entites.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class OurUserInfoDetails implements UserDetails {
    private String email;
    private String password;
    private List<GrantedAuthority> roles;

    public OurUserInfoDetails(User user)
    {
        email= user.getEmail();
        password=user.getPassword();
        String role = user.getRole().toString();
        System.out.println("OurUserInfoDetails - User role: " + role);
        // Use Spring Security's SimpleGrantedAuthority for proper role handling
        roles = List.of(new SimpleGrantedAuthority("ROLE_" + role));
        System.out.println("OurUserInfoDetails - Authorities created: " + roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true ;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
