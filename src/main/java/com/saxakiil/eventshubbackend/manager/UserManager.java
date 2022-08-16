//package com.saxakiil.eventshubbackend.manager;
//
//import com.saxakiil.eventshubbackend.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class UserManager {
//
//    private final UserService userService;
//
//    public UserDetailsImpl getUser() {
//        return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal());
//    }
//
//    public boolean existUser() {
//        return userService.getById(getUser().getId()).isPresent();
//    }
//}
