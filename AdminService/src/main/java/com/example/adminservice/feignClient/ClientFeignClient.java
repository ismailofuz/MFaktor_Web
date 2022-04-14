package com.example.adminservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ClientService")
public interface ClientFeignClient {

}
