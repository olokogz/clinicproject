package com.clinic.clinic_project.security.services;

import com.clinic.clinic_project.controller.ClinicUserRepository;
import com.clinic.clinic_project.model.Clinic_users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClinicUserRepository clinicUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Clinic_users clinic_users = clinicUserRepository.findByUsername(s).orElseThrow(
                () -> new UsernameNotFoundException("User not found with -> username" + s));

        return UserPrinciple.build(clinic_users);

        }
}
