package com.example.clientservice.service;

import com.example.clientservice.entity.AdsSource;
import com.example.clientservice.entity.User;
import com.example.clientservice.feignClient.CatalogFeignClient;
import com.example.clientservice.payload.ApiResponse;
import com.example.clientservice.payload.UserDto;
import com.example.clientservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    final CatalogFeignClient catalogFeignClient;
    final UserRepository userRepository;

    public ApiResponse addUser(UserDto user){
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
        ApiResponse one = catalogFeignClient.getOne(user.getAdsSourseId());
        AdsSource object = (AdsSource) one.getObject();
        addUser.setAdsSource(object);
        User save = userRepository.save(addUser);
        return new ApiResponse("Registered",true);
    }
}
