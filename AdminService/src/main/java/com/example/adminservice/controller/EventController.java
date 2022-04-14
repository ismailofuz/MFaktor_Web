package com.example.adminservice.controller;

import com.example.adminservice.payload.ApiResponse;
import com.example.adminservice.payload.EventDto;
import com.example.adminservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    final EventService eventService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody EventDto eventDto){
        ApiResponse apiResponse = eventService.add(eventDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 204).body(apiResponse);
    }

}
