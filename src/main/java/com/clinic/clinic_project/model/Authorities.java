package com.clinic.clinic_project.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Authorities {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long authority_id;
    @NotBlank
    private RoleName userRoleName;
    @NotBlank
    private String description;
    @ManyToMany(mappedBy = "authorities")
    private List<Clinic_users> clinic_users = new ArrayList<>();

    public Authorities() {
    }

    public Authorities(RoleName userRoleName, String description) {
        this.userRoleName = userRoleName;
        this.description = description;
    }

    public Long getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(Long authority_id) {
        this.authority_id = authority_id;
    }

    public RoleName getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(RoleName userRoleName) {
        this.userRoleName = userRoleName;
    }

    public List<Clinic_users> getClinic_users() {
        return clinic_users;
    }

    public void setClinic_users(List<Clinic_users> clinic_users) {
        this.clinic_users = clinic_users;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "authority_id=" + authority_id +
                ", user_role_name='" + userRoleName + '\'' +
                ", clinic_users=" + clinic_users +
                '}';
    }
}
