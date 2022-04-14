package com.example.adminservice.controller;

import com.example.adminservice.payload.ApiResponse;
import com.example.adminservice.payload.SeatDto;
import com.example.adminservice.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
@RequiredArgsConstructor
public class SeatController {

    final SeatService seatService;

    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse apiResponse = seatService.getAll();
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAll(@PathVariable Integer id){
        ApiResponse apiResponse = seatService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody SeatDto seatDto){
        ApiResponse apiResponse = seatService.add(seatDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody SeatDto seatDto){
        ApiResponse apiResponse = seatService.update(id, seatDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id){
        ApiResponse apiResponse = seatService.deleted(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


}
