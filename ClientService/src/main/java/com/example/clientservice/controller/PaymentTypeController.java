package com.example.clientservice.controller;

import com.example.clientservice.entity.PaymentColor;
import com.example.clientservice.entity.PaymentType;
import com.example.clientservice.payload.ApiResponse;
import com.example.clientservice.payload.PaymentTypeDto;
import com.example.clientservice.repository.PaymentColorRepository;
import com.example.clientservice.repository.PaymentTyPeRepository;
import com.example.clientservice.service.PaymentColorService;
import com.example.clientservice.service.PaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/paymenttype")
public class PaymentTypeController {

    //catalog serviceda qilish kerak

    final PaymentTypeService paymentTypeService;
    final PaymentTyPeRepository paymentTyPeRepository;
    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(paymentTyPeRepository.findAll());
    }

   @GetMapping("/{id}")
   public HttpEntity<?> getById(@PathVariable Integer id){
       Optional<PaymentType> optionalPaymentType = paymentTyPeRepository.findById(id);
       return ResponseEntity.status(optionalPaymentType.isPresent()?200:404).body(optionalPaymentType.orElseThrow());
   }
    @PostMapping
    public HttpEntity<?> addPaymentType(@RequestBody PaymentTypeDto dto){
        ApiResponse apiResponse=paymentTypeService.addPaymentType(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editById(@PathVariable Integer id,@RequestBody PaymentTypeDto dto){
        ApiResponse apiResponse = paymentTypeService.editPaymentType(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200: 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Integer id){
        paymentTyPeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }












}
