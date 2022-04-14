package com.example.botservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ClientService")
public interface ClientFeignClient {

}
