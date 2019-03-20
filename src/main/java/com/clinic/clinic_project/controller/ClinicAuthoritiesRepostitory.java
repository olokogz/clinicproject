package com.clinic.clinic_project.controller;

import com.clinic.clinic_project.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicAuthoritiesRepostitory extends JpaRepository<Authorities,Long> {

    Optional<Authorities> findByuserRoleName(String userRoleName);
}
