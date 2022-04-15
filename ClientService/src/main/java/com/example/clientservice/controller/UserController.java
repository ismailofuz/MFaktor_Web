package com.example.clientservice.controller;

import com.example.clientservice.entity.User;
import com.example.clientservice.payload.ApiResponse;
import com.example.clientservice.payload.PaymentDto;
import com.example.clientservice.payload.UserDto;
import com.example.clientservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    final UserService userService;
    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody UserDto user){
        ApiResponse apiResponse = userService.addUser(user);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PostMapping("/register/reception")
    public HttpEntity<?> ReceptionUser(@RequestBody UserDto user, @RequestBody PaymentDto paymentDto){
        ApiResponse apiResponse = userService.paymentUser(user, paymentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("register/reception/{id}")
    public HttpEntity<?> EditAppliedUser(@PathVariable Integer id,@RequestBody UserDto userDto){
        userService.editAppliedUser(id)
    }


}
