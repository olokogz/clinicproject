package com.clinic.clinic_project.service;

import com.clinic.clinic_project.controller.ClinicUserRepository;
import com.clinic.clinic_project.model.Authorities;
import com.clinic.clinic_project.model.Clinic_users;
import com.clinic.clinic_project.security.services.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.transaction.Transactional;
import java.nio.file.attribute.UserPrincipal;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private ClinicUserRepository clinicUserRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Clinic_users clinic_users = clinicUserRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

        return UserPrinciple.build(clinic_users);

    }

   /* private Set<GrantedAuthority> convertAuthorities(Set<Authorities> authorities){
        Set<GrantedAuthority> role = new HashSet<>();
        for(Authorities aut : authorities)
        {
            role.add(new SimpleGrantedAuthority(aut.getUser_role_name()));
        }
        return role;
    }*/
}
