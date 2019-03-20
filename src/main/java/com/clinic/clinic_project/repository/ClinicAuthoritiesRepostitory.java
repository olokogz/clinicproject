package com.clinic.clinic_project.repository;

import com.clinic.clinic_project.model.Authorities;
import com.clinic.clinic_project.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicAuthoritiesRepostitory extends JpaRepository<Authorities,Long> {

    Optional<Authorities> findByUserRoleName(RoleName roleName);
}
