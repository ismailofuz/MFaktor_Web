package com.example.clientservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "BotService")
public interface BotFeignClient {

}
