package com.clinic.clinic_project.repository;


import com.clinic.clinic_project.model.Clinic_users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicUserRepository extends JpaRepository<Clinic_users, Long> {

    Optional<Clinic_users> findByUsername(String username);
    Boolean existsByUsername(String username);
    List<Clinic_users> findAll();

}
