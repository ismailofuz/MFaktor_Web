package com.example.clientservice.controller;

import com.example.clientservice.entity.User;
import com.example.clientservice.payload.ApiResponse;
import com.example.clientservice.payload.PaymentDto;
import com.example.clientservice.payload.UserDto;
import com.example.clientservice.repository.UserRepository;
import com.example.clientservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    final UserRepository userRepository;
    final UserService userService;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody UserDto user) {
        ApiResponse apiResponse = userService.addUser(user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PostMapping("/register/reception")
    public HttpEntity<?> ReceptionUser(@RequestBody UserDto user, @RequestBody PaymentDto paymentDto) {
        ApiResponse apiResponse = userService.paymentUser(user, paymentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("register/reception/{id}")
    public HttpEntity<?> EditAppliedUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.editAppliedUser(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll() {
        ApiResponse response = userService.getAll();
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @GetMapping("/getAllByChatId")
    public HttpEntity<?> getAllByChatId() {
        ApiResponse response = userService.getAllByChatId();
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @GetMapping("/byChatId/{chatId}")
    public HttpEntity<?> getByChatId(@PathVariable String chatId) {
        Optional<User> optionalUser = userRepository.findByChatId(Long.valueOf(chatId));
        return ResponseEntity.ok(optionalUser);
    }

}
