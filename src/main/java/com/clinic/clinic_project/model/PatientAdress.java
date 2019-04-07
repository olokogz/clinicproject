package com.clinic.clinic_project.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PatientAdress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_patient_adress")
    private Long id;
    @NotBlank
    private String street;
    @NotBlank
    private String homeNumber;
    private String flatNumber;
    @NotBlank
    private String city;
    @NotBlank
    private String postalCode;
    @ManyToMany(mappedBy = "patientAdress")
    @JsonIgnore
    private List<Clinic_users> clinic_users = new ArrayList<>();
    public PatientAdress() {
    }

    public PatientAdress(@NotBlank String street, @NotBlank String homeNumber, @NotBlank String flatNumber, @NotBlank String city, @NotBlank String postalCode) {
        this.street = street;
        this.homeNumber = homeNumber;
        this.flatNumber = flatNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
