package com.saxakiil.eventshubbackend.controller;

import com.saxakiil.eventshubbackend.dto.auth.JwtResponse;
import com.saxakiil.eventshubbackend.dto.auth.LoginRequest;
import com.saxakiil.eventshubbackend.dto.auth.MessageResponse;
import com.saxakiil.eventshubbackend.dto.auth.SignupRequest;
import com.saxakiil.eventshubbackend.model.AccountProfile;
import com.saxakiil.eventshubbackend.model.Role;
import com.saxakiil.eventshubbackend.model.User;
import com.saxakiil.eventshubbackend.repository.UserRepository;
import com.saxakiil.eventshubbackend.service.UserDetailsImpl;
import com.saxakiil.eventshubbackend.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        if (!userRepository.existsByEmail(loginRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is not found"));
        }

        User username = userRepository
                .findByEmail(loginRequest.getEmail()).get();

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Role role = userRepository.findById(userDetails.getId()).get().getRole();

        return ResponseEntity.ok(JwtResponse.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .role(role)
                .email(userDetails.getEmail())
                .build());
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signupUserRequest) {

        if (userRepository.existsByUsername(signupUserRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }

        if (userRepository.existsByEmail(signupUserRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exist"));
        }

        if (userRepository.existsByAccountProfile_PhoneNumber(signupUserRequest.getProfileRequest().getPhoneNumber())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Phone number is exist"));
        }

        User user = User.builder()
                .username(signupUserRequest.getUsername())
                .email(signupUserRequest.getEmail())
                .password(passwordEncoder.encode(signupUserRequest.getPassword()))
                .accountProfile(AccountProfile.builder()
                        .phoneNumber(signupUserRequest.getProfileRequest().getPhoneNumber())
                        .firstName(signupUserRequest.getProfileRequest().getFirstName())
                        .lastName(signupUserRequest.getProfileRequest().getLastName())
                        .birthday(signupUserRequest.getProfileRequest().getBirthday())
                        .build())
                .build();

        userRepository.save(user);
        return ResponseEntity
                .ok(new MessageResponse("User CREATED"));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<MessageResponse> logout(HttpServletRequest request) {
        if (request.getUserPrincipal() == null) {
            return new ResponseEntity<>(new MessageResponse("User is not authorized"),
                    HttpStatus.BAD_REQUEST);
        }
        try {
            request.logout();
        } catch (ServletException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity
                .ok(new MessageResponse("logout"));
    }
}