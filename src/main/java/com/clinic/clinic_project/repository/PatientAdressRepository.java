package com.clinic.clinic_project.repository;


import com.clinic.clinic_project.model.PatientAdress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientAdressRepository extends JpaRepository<PatientAdress, Long> {
}
