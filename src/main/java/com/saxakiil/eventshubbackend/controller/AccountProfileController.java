//package com.saxakiil.eventshubbackend.controller;
//
//import com.saxakiil.eventshubbackend.dto.auth.MessageResponse;
//import com.saxakiil.eventshubbackend.dto.auth.ProfileRequest;
//import com.saxakiil.eventshubbackend.model.AccountProfile;
//import com.saxakiil.eventshubbackend.model.User;
//import com.saxakiil.eventshubbackend.repository.UserRepository;
//import com.saxakiil.eventshubbackend.service.UserDetailsImpl;
//import com.saxakiil.eventshubbackend.transformer.AccountProfileTransformer;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.lang.NonNull;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/profile")
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class AccountProfileController {
//
//    private final UserRepository userRepository;
//    private final AccountProfileTransformer accountProfileTransformer;
//
//    @GetMapping("/get")
//    @PreAuthorize(value = "hasAnyRole('MODERATOR', 'USER')")
//    public ResponseEntity<AccountProfile> getProfile() {
//
//        UserDetailsImpl authUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//
//        return ResponseEntity.ok(userRepository.findByUsername(authUser.getUsername()).get().getAccountProfile());
//    }
//
//    @PostMapping("/edit")
//    @PreAuthorize(value = "hasAnyRole('USER')")
//    public ResponseEntity<?> editProfile(@RequestBody ProfileRequest profileRequest) {
//
//        UserDetailsImpl authUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//
//        User user = userRepository.findByUsername(authUser.getUsername()).get();
//
//        AccountProfile updatedAccountProfile = accountProfileTransformer
//                .transform(user.getAccountProfile().getId(), profileRequest);
//        user.setAccountProfile(updatedAccountProfile);
//        userRepository.save(user);
//
//        return ResponseEntity.ok(new MessageResponse("Account profile is UPDATED"));
//    }
//}
