package com.example.clientservice.feignClient;

import com.example.clientservice.payload.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CATALOG")
public interface CatalogFeignClient {

//    @GetMapping("/adsSource")
//    ApiResponse getAll();

    @GetMapping("/adsSourse/{id}")
    ApiResponse getOne(@PathVariable  Integer id);
}
