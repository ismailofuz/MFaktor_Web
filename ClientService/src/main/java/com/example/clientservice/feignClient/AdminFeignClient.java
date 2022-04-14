package com.example.clientservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminService")
public interface AdminFeignClient {

}
