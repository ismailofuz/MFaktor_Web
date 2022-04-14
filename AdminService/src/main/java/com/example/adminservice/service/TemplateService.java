package com.example.adminservice.service;

import com.example.adminservice.entity.Seat;
import com.example.adminservice.entity.Template;
import com.example.adminservice.payload.ApiResponse;
import com.example.adminservice.payload.RawDto;
import com.example.adminservice.payload.TemplateDto;
import com.example.adminservice.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;

    public ApiResponse one(Integer id) {

        Optional<Template> byId = templateRepository.findById(id);
        if (byId.isPresent()) {
            return new ApiResponse("Found", true, byId.get());
        } else {
            return new ApiResponse("Not found", false);
        }
    }

    public ApiResponse add(TemplateDto dto) {
        Optional<Template> optionalTemplate = templateRepository.findByName(dto.getName());
        if (optionalTemplate.isPresent()) {
            return new ApiResponse("Template already exists", false);
        } else {
            Template template = new Template();

            if (dto.getMaxPrice() <= dto.getMinPrice()) {
                return new ApiResponse("something went wrong", false);
            }
            // checking

            Double maxPrice = dto.getMaxPrice();
            Double minPrice = dto.getMinPrice();
            Double difference = (maxPrice - minPrice) / dto.getRawDtoList().size();

            List<RawDto> rawDtoList = dto.getRawDtoList();
            List<Seat> seatList = new ArrayList<>();
            for (int i = 0; i < rawDtoList.size(); i++) {
                String str = "" + ('A' + i);
                for (Integer integer = 0; integer < rawDtoList.get(i).getCount(); integer++) {
                    Seat seat = new Seat();
                    seat.setName(i + str);
                    seat.setRaw(rawDtoList.get(i).getRawNumber());
                    if (dto.isPriceByPlace()) {
                        seat.setPrice(maxPrice - difference * i);
                    } else {
                        seat.setPrice(maxPrice);
                    }
                    seatList.add(seat);
                }

            }
            template.setSeats(seatList);

            template.setName(dto.getName());
            template.setCount(dto.getCount());
            template.setMaxPrice(dto.getMaxPrice());
            template.setMinPrice(dto.getMinPrice());
            template.setPriceByPlace(dto.isPriceByPlace());

            // stulchalar generate :


            Template save = templateRepository.save(template);

            return new ApiResponse("added", true, save);
        }
    }
}
