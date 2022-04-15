package com.example.catalogservice.controller;

import com.example.catalogservice.payload.ApiResponse;
import com.example.catalogservice.payload.VaucherDto;
import com.example.catalogservice.repository.VaucherRepository;
import com.example.catalogservice.service.VaucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vaucher")
public class VaucherController {
    @Autowired
    VaucherRepository vaucherRepository;
    @Autowired
    VaucherService vaucherService;
    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse all = vaucherService.getAll();
        return ResponseEntity.ok(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getById(Integer id){
        ApiResponse byId = vaucherService.getById(id);
        return ResponseEntity.ok(byId);
    }
    @PostMapping
    public  HttpEntity<?> addVaucher(@RequestBody VaucherDto vaucherDto){
        ApiResponse apiResponse = vaucherService.addVaucher(vaucherDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED :HttpStatus.CONFLICT).body(apiResponse);
    }

}
