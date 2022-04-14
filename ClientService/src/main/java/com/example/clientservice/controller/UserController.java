package com.example.clientservice.controller;

import com.example.clientservice.entity.User;
import com.example.clientservice.payload.ApiResponse;
import com.example.clientservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    final UserService userService;
    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody User user){
        ApiResponse apiResponse = userService.addUser(user);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }


}
