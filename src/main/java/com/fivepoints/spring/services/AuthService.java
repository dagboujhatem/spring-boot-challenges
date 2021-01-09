package com.fivepoints.spring.services;

import com.fivepoints.spring.entities.User;
import com.fivepoints.spring.payload.requests.LoginRequest;
import com.fivepoints.spring.payload.requests.SignupRequest;
import com.fivepoints.spring.payload.responses.MessageResponse;
import com.fivepoints.spring.repositories.RoleRepository;
import com.fivepoints.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String login(LoginRequest loginRequest){
        return "";
    }
    public ResponseEntity<?> register(SignupRequest signupRequest){

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: E-mail is already taken!"));
        }
        // Create new user's account
        User newUser = new User();
        newUser.setFirstName(signupRequest.getFirstName());
        newUser.setLastName(signupRequest.getLastName());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        newUser.setAge(signupRequest.getAge());

        // Save newUser and return success response
        userRepository.save(newUser);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
