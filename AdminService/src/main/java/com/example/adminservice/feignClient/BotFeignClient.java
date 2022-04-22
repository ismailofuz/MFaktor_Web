package com.example.adminservice.feignClient;

import com.example.adminservice.entity.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.GET;

@FeignClient(name = "BOT")
public interface BotFeignClient {


    @PostMapping("/sendMessageUsers")
    public void setAllMessage(@RequestBody Event event);

}
