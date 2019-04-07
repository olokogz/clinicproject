package com.clinic.clinic_project.controller;


import com.clinic.clinic_project.message.request.LoginForm;
import com.clinic.clinic_project.message.request.SignUpForm;
import com.clinic.clinic_project.message.response.JwtResponse;

import com.clinic.clinic_project.message.response.ResponseMessage;
import com.clinic.clinic_project.model.Authorities;
import com.clinic.clinic_project.model.Clinic_users;
import com.clinic.clinic_project.model.RoleName;
import com.clinic.clinic_project.repository.ClinicAuthoritiesRepostitory;
import com.clinic.clinic_project.repository.ClinicUserRepository;
import com.clinic.clinic_project.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPI {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ClinicUserRepository clinicUserRepository;

    @Autowired
    ClinicAuthoritiesRepostitory clinicAuthoritiesRepostitory;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest)
    {
        if(clinicUserRepository.existsByUsername(signUpRequest.getUsername()))
        {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        //Creating users account
        Boolean temp = true;
        switch(signUpRequest.getEnabled()){
            case "active": {
                temp = true;
                break;
            }
            case "disactive":{
                temp = false;
                break;
            }

        }
        Clinic_users clinic_users = new Clinic_users(signUpRequest.getUsername(),passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getName(),signUpRequest.getSurename(),
                signUpRequest.getPhoneNumber(), signUpRequest.getEmailAddress(), signUpRequest.getBirthdate(), signUpRequest.getGender(),temp);

        Set<String> strRoles = signUpRequest.getRole();
        Set<Authorities> roles = new HashSet<>();

        strRoles.forEach( x -> {
            switch(x){
                case "admin":
                    Authorities adminRole = clinicAuthoritiesRepostitory.findByUserRoleName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail -> Cause: User Role not found."));

                    roles.add(adminRole);
                    break;

                case "pm":
                    Authorities pmRole = clinicAuthoritiesRepostitory.findByUserRoleName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail -> Cause: User Role not found."));
                    roles.add(pmRole);
                    break;

                case "user":
                    Authorities userRole = clinicAuthoritiesRepostitory.findByUserRoleName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail -> Cause: User Role not found."));
                    roles.add(userRole);
                    break;
            }
        });
        clinic_users.setAuthorities(roles);
        clinicUserRepository.save(clinic_users);

        return new ResponseEntity<>(new ResponseMessage("User registered succesfully!"),HttpStatus.OK);
    }




}
