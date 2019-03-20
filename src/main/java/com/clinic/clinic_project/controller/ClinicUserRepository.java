package com.clinic.clinic_project.controller;


import com.clinic.clinic_project.model.Clinic_users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface ClinicUserRepository extends JpaRepository<Clinic_users, Long> {

    Optional<Clinic_users> findByUsername(String username);
    Boolean existByUsername(String username);

}
