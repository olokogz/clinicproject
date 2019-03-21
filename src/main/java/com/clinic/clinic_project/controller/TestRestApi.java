package com.clinic.clinic_project.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
public class TestRestApi {

    @GetMapping("test/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess()
    {
        return ">>>User Contents!";
    }

    @GetMapping("test/pm")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public String projectManagementAccess()
    {
        return ">>>PM Contents!";
    }


    @GetMapping("test/admin")
    @PreAuthorize(" hasRole('ADMIN')")
    public String adminAccess()
    {
        return ">>>Admin Contents!";
    }


}
