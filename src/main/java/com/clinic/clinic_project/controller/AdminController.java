package com.clinic.clinic_project.controller;



import com.clinic.clinic_project.model.Clinic_users;
import com.clinic.clinic_project.repository.ClinicAuthoritiesRepostitory;
import com.clinic.clinic_project.repository.ClinicUserRepository;
import com.clinic.clinic_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {


    @Autowired
    ClinicUserRepository clinicUserRepository;

    @Autowired
    ClinicAuthoritiesRepostitory clinicAuthoritiesRepostitory;

    @GetMapping("/findAll")
    public ResponseEntity<?> getAllUsers()
    {
        return ResponseEntity.ok(clinicUserRepository.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(clinicUserRepository.findById(id));
    }
    @PostMapping("delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        clinicUserRepository.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

}
