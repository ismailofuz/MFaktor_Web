package com.example.adminservice.feignClient;

import com.example.adminservice.entity.Event;
import com.example.adminservice.entity.Seat;
import com.example.adminservice.entity.Visitor;
import com.example.adminservice.payload.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CLIENT")
public interface ClientFeignClient {
//@GetMapping("visitor/{id}")
    @GetMapping("/visitor/check")
    public ApiResponse checkVisitor(@RequestParam String phoneNumber);
    @PostMapping("/visitor/event/add")
    public ApiResponse save(@RequestBody Visitor visitor);

}
