package com.example.clientservice.feignClient;

import com.example.clientservice.payload.ApiResponse;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CATALOG")
public interface CatalogFeignClient {

//    @GetMapping("/adsSource")
//    ApiResponse getAll();

    @GetMapping("/adsSource/{id}")
    ApiResponse getAdsSource(@PathVariable Integer id);

    @GetMapping("/paymentType/{id}")
    ApiResponse getPaymentType(@PathVariable Integer id);

}
