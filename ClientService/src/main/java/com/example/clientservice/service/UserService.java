package com.example.clientservice.service;

import com.example.clientservice.entity.User;
import com.example.clientservice.payload.ApiResponse;
import com.example.clientservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;
    public ApiResponse addUser(User user){
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            return new ApiResponse("User with this phoneNumber already exists",false);
        }
        User addUser=new User();
        addUser.setFirstName(user.getFirstName());
        addUser.setLastName(user.getLastName());
        addUser.setGender(user.getGender());
        addUser.setOrganization(user.getOrganization());
        addUser.setPassword(user.getPassword());
        addUser.setPhoneNumber(user.getPhoneNumber());
        User save = userRepository.save(addUser);
        return new ApiResponse("Registered",true);
    }
}
