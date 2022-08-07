//package com.saxakiil.eventshubbackend.controller;
//
//import com.saxakiil.eventshubbackend.dto.auth.MessageResponse;
//import com.saxakiil.eventshubbackend.dto.profile.OrganizeProfileResponse;
//import com.saxakiil.eventshubbackend.model.Card;
//import com.saxakiil.eventshubbackend.model.EnumRole;
//import com.saxakiil.eventshubbackend.model.User;
//import com.saxakiil.eventshubbackend.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.lang.NonNull;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Set;
//
//@RestController
//@RequestMapping("/api/organize")
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class OrganizationController {
//
//    private final UserService userService;
//
//    @GetMapping("/getOrganizationById")
//    public ResponseEntity<?> getOrganizationById(@NonNull @RequestParam Long id) {
//
//        if (userService.getById(id).isEmpty()) {
//            return ResponseEntity.badRequest()
//                    .body(new MessageResponse(String.format("Error: User with id='%s' not found", id)));
//        }
//
//        User organize = userService.getById(id).get();
//
//        OrganizeProfileResponse organizeProfileResponse = OrganizeProfileResponse.builder()
//                .username(organize.getUsername())
//                .email(organize.getEmail())
//                .phoneNumber(organize.getAccountProfile().getPhoneNumber())
//                .address(organize.getAccountProfile().getAddress())
//                .urlOnSite(organize.getAccountProfile().getUrlOnSite())
//                .build();
//
//        return organize.getRole().getName().equals(EnumRole.ROLE_ORGANIZE) ?
//                ResponseEntity.ok(organizeProfileResponse) : ResponseEntity.badRequest()
//                .body(new MessageResponse("Error: This user is not organization"));
//    }
//
//    @SneakyThrows
//    @GetMapping(value = "/getOrganizationCardsByUsername")
//    public ResponseEntity<Set<Card>> getOrganizationCardsByUsername(@NonNull @RequestParam String username) {
//        if (userService.getByUsername(username).isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        Set<Card> cardSet = userService.getByUsername(username).get().getOrganizeCard();
//        return new ResponseEntity<>(cardSet, HttpStatus.OK);
//    }
//}
