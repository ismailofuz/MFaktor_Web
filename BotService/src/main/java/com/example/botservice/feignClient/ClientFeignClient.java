package com.example.botservice.feignClient;

import com.example.botservice.payload.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ClientService")
public interface ClientFeignClient {

    @GetMapping("/getAll")
    ApiResponse getAll();

    @GetMapping("/getAllByChatId")
    ApiResponse getAllByChatId();

}
