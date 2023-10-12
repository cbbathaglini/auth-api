package com.example.auth.controllers;

import com.example.auth.domain.user.RegisterDTO;
import com.example.auth.domain.user.User;
import com.example.auth.domain.user.AuthenticationDTO;
import com.example.auth.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authentication){

        var usernamePassword = new UsernamePasswordAuthenticationToken(authentication.login(),authentication.password());
        var auth = authenticationManager.authenticate(usernamePassword);


        return ResponseEntity.ok().build();
    }



    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody @Valid RegisterDTO register){

        if(this.userRepository.findByLogin(register.login()) != null ) return ResponseEntity.badRequest().build();

        User newUser = register.convert();
        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}