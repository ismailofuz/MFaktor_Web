package com.example.clientservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CatalogService")
public interface CatalogFeignClient {

}
