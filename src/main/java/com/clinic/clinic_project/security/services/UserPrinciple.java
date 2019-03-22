package com.clinic.clinic_project.security.services;

import com.clinic.clinic_project.model.Clinic_users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
    private Long id;
    private String username;
    private boolean enabled;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;




    public UserPrinciple(Long id, String username, boolean enabled, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.enabled = enabled;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple build(Clinic_users clinic_users)
    {
        List<GrantedAuthority> authorities = clinic_users.getAuthorities().stream().map(role ->
                new SimpleGrantedAuthority(role.getUserRoleName().name())).collect(Collectors.toList());

        return new UserPrinciple(
                clinic_users.getId(),
                clinic_users.getUsername(),
                clinic_users.getEnabled(),
                clinic_users.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
