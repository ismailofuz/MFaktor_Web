package com.example.catalogservice.service;


import com.example.catalogservice.entity.PaymentColor;
import com.example.catalogservice.entity.PaymentType;
import com.example.catalogservice.payload.ApiResponse;
import com.example.catalogservice.payload.PaymentTypeDto;
import com.example.catalogservice.repository.PaymentColorRepository;
import com.example.catalogservice.repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentTypeService {
    @Autowired
    PaymentTypeRepository paymentTypeRepository;
    @Autowired
    PaymentColorRepository paymentColorRepository;

    public ApiResponse getAll() {
        List<PaymentType> all = paymentTypeRepository.findAll();
        return new ApiResponse("All", true, all);
    }


    public ApiResponse getOne(Integer id) {
        Optional<PaymentType> byId = paymentTypeRepository.findById(id);
        if (byId.isEmpty()) {
            return new ApiResponse("not found", false);
        }
        return new ApiResponse("topildi", true, byId.get());
    }

    public ApiResponse create(PaymentTypeDto dto) {
        boolean existsByName = paymentTypeRepository.existsByName(dto.getName());
        if (existsByName) {
            return new ApiResponse("Bunday tulov turi oldin mavjud", false);
        }
        PaymentType paymentType = new PaymentType();
        paymentType.setActive(dto.isActive());
        paymentType.setName(dto.getName());
        Optional<PaymentColor> optionalPaymentColorpaymentColor = paymentColorRepository.findById(dto.getPaymentColorId());
        if (optionalPaymentColorpaymentColor.isEmpty()) {
            return new ApiResponse("Bunday tulov rangi mavjud emas", false);
        }
        paymentType.setPaymentColor(optionalPaymentColorpaymentColor.get());
        PaymentType savedPaymentType = paymentTypeRepository.save(paymentType);
        return new ApiResponse("Success", true, savedPaymentType);


    }
}
