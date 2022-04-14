package com.example.clientservice.service;

import com.example.clientservice.entity.PaymentColor;
import com.example.clientservice.entity.PaymentType;
import com.example.clientservice.payload.ApiResponse;
import com.example.clientservice.payload.PaymentTypeDto;
import com.example.clientservice.repository.PaymentColorRepository;
import com.example.clientservice.repository.PaymentTyPeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentTypeService {
    final PaymentTyPeRepository paymentTyPeRepository;
    final PaymentColorRepository paymentColorRepository;
    public ApiResponse addPaymentType(PaymentTypeDto dto) {
        if (paymentTyPeRepository.existsByName(dto.getName())) {
            return new ApiResponse("This paymentType already exists",false);
        }

        Optional<PaymentColor> optionalPaymentColor = paymentColorRepository.findById(dto.getPaymentColorId());
        if (optionalPaymentColor.isPresent()) {
            PaymentColor paymentColor = optionalPaymentColor.get();
            PaymentType paymentType =new PaymentType();
            paymentType.setPaymentColor(paymentColor);
            paymentType.setName(dto.getName());
            paymentType.setActive(dto.isActive());
            PaymentType save = paymentTyPeRepository.save(paymentType);
            return new ApiResponse("Added successfully",true,save);

        }
        return new ApiResponse("PaymentColor not found",false);



    }

    public ApiResponse editPaymentType(Integer id,PaymentTypeDto dto){
        if (paymentTyPeRepository.existsByNameAndIdNot(dto.getName(), id)) {
            return new ApiResponse("This paymentType already exists",false);

        }
        Optional<PaymentType> optionalPaymentType = paymentTyPeRepository.findById(id);
        if (optionalPaymentType.isPresent()) {
            PaymentType paymentType = optionalPaymentType.get();
            paymentType.setName(dto.getName());
            paymentType.setActive(dto.isActive());
            paymentType.setPaymentColor(paymentColorRepository.findById(dto.getPaymentColorId()).get());
            PaymentType save = paymentTyPeRepository.save(paymentType);

            return new ApiResponse("Updated",true,save);



        }
        return new ApiResponse("PaymentType not found",true);



    }
}
