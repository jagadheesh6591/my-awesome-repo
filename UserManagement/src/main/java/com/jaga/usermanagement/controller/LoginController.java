package com.jaga.usermanagement.controller;

import com.jaga.usermanagement.service.UserService;
import com.jaga.usermanagement.view.LoginSuccess;
import com.jaga.usermanagement.view.UserLogin;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/login")
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public HttpEntity<LoginSuccess> login(@RequestBody UserLogin userLogin) {
        userService.validateLogin(userLogin);
        LoginSuccess success = LoginSuccess.builder().token("@34fef@DSR@FDDF#DGDG#R@#EER#$#%DF1223").build();
        return ResponseEntity.ok(success);
    }

}
