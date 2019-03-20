package com.clinic.clinic_project.service;

import com.clinic.clinic_project.controller.ClinicAuthoritiesRepostitory;
import com.clinic.clinic_project.controller.ClinicUserRepository;
import com.clinic.clinic_project.model.Authorities;
import com.clinic.clinic_project.model.Clinic_users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
    private static final String DEFAULT_ROLE = "ROLE_USER";
    private ClinicUserRepository clinicUserRepository;
    private ClinicAuthoritiesRepostitory clinicAuthoritiesRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setClinicUserRepository(ClinicUserRepository clinicUserRepository)
    {
        this.clinicUserRepository = clinicUserRepository;
    }

    @Autowired
    public void addWithDefaultRole(Clinic_users clinic_users)
    {
        Authorities defaultRole = clinicAuthoritiesRepository.findByuserRoleName(DEFAULT_ROLE);
        clinic_users.getAuthorities().add(defaultRole);
        String passwordHash = passwordEncoder.encode(clinic_users.getPassword());
        clinic_users.setPassword(passwordHash);
        clinicUserRepository.save(clinic_users);
    }
}
