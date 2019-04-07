package com.clinic.clinic_project.security.services;

import com.clinic.clinic_project.model.Clinic_users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
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
    private String name;
    private String surename;
    private String phoneNumber;
    private String emailAddress;
    private LocalDate birthdate;
    private String gender;



    public UserPrinciple(Long id, String username, boolean enabled, String password,String name,String surename,String phoneNumber,
                         String emailAddress,LocalDate birthdate,String gender, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.enabled = enabled;
        this.password = password;
        this.name = name;
        this.surename = surename;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.birthdate = birthdate;
        this.gender = gender;
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
                clinic_users.getName(),
                clinic_users.getSurename(),
                clinic_users.getPhoneNumber(),
                clinic_users.getEmailAddress(),
                clinic_users.getBirthdate(),
                clinic_users.getGender(),
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
