package com.example.catalogservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminService")
public interface AdminFeignClient {

}
