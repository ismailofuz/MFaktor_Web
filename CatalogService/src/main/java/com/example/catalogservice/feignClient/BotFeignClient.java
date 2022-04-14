package com.example.catalogservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "BotService")
public interface BotFeignClient {

}
