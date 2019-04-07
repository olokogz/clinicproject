package com.clinic.clinic_project.model;




import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames ={"username"})})
public class Clinic_users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clinic_user")
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String surename;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String emailAddress;
    private LocalDate birthdate;
    @NotBlank
    private String gender;
    private Boolean enabled;
    private LocalDate accountCreationDate;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "client_roles",
            joinColumns = {@JoinColumn(name="clinic_user_id",referencedColumnName = "id_clinic_user")},
            inverseJoinColumns = {@JoinColumn(name="id_authority", referencedColumnName = "authority_id")})
    private Set<Authorities> authorities = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name="patient_adresses",
            joinColumns = {@JoinColumn(name="clinic_user_id", referencedColumnName = "id_clinic_user")},
            inverseJoinColumns = {@JoinColumn(name="patient_adress_id", referencedColumnName = "id_patient_adress")})
    private Set<PatientAdress> patientAdress = new HashSet<>();


    Clinic_users() {
    }

    public Clinic_users(@NotBlank String username, @NotBlank String password, @NotBlank String name, @NotBlank String surename, @NotBlank String phoneNumber,
                        @NotBlank String emailAddress, @NotBlank LocalDate birthdate, @NotBlank String gender, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surename = surename;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.birthdate = birthdate;
        this.gender = gender;
        this.enabled = enabled;
        this.accountCreationDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<PatientAdress> getPatientAdress() {
        return patientAdress;
    }

    public void setPatientAdress(Set<PatientAdress> patientAdress) {
        this.patientAdress = patientAdress;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public Set<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }
}
