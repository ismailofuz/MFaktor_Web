package com.example.adminservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "BotService")
public interface BotFeignClient {

}
