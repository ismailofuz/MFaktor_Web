package com.example.adminservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "CLIENT")
public interface ClientFeignClient {
//@GetMapping("visitor/{id}")

}
