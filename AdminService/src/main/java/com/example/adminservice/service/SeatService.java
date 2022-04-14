package com.example.adminservice.service;

import com.example.adminservice.entity.Event;
import com.example.adminservice.entity.Seat;
import com.example.adminservice.entity.Template;
import com.example.adminservice.payload.ApiResponse;
import com.example.adminservice.payload.SeatDto;
import com.example.adminservice.repository.EventRepository;
import com.example.adminservice.repository.SeatRepository;
import com.example.adminservice.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatService {

    final SeatRepository seatRepository;
    final EventRepository eventRepository;
    final TemplateRepository templateRepository;


    public ApiResponse getAll() {
        List<Seat> all = seatRepository.findAll();

        if (all.isEmpty()){
            return new ApiResponse("Seatlar listi bo'sh", false);
        }

        List<SeatDto> seatDtoList = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {

            Seat seat = all.get(i);

            SeatDto seatDto = new SeatDto();

            seatDto.setName(seat.getName());
            seatDto.setBookedDate(seat.getBookedDate());
            seatDto.setEventId(seat.getEvent().getId());
            seatDto.setPrice(seat.getPrice());
            seatDto.setExpireDate(seat.getExpireDate());
            seatDto.setRaw(seat.getRaw());
            seatDto.setStatus(seat.getStatus());
            seatDto.setTemplateId(seat.getTemplate().getId());

            seatDtoList.add(seatDto);
        }

        return new ApiResponse("Seates list", true, seatDtoList);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Seat> seatOptional = seatRepository.findById(id);
        if (!seatOptional.isPresent()) {
            return new ApiResponse("Bunday id dagi seat mavjud emas", false);
        }

        Seat seat = seatOptional.get();

        SeatDto seatDto = new SeatDto();

        seatDto.setName(seat.getName());
        seatDto.setBookedDate(seat.getBookedDate());
        seatDto.setEventId(seat.getEvent().getId());
        seatDto.setPrice(seat.getPrice());
        seatDto.setExpireDate(seat.getExpireDate());
        seatDto.setRaw(seat.getRaw());
        seatDto.setStatus(seat.getStatus());
        seatDto.setTemplateId(seat.getTemplate().getId());

        return new ApiResponse("Mana", true, seatDto);
    }

    public ApiResponse add(SeatDto seatDto) {

        //Event
        Integer eventId = seatDto.getEventId();
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (!eventOptional.isPresent()) {
            return new ApiResponse("Bunday id dagi event mavjud emas", false);
        }
        Event event = eventOptional.get();

        Integer templateId = seatDto.getTemplateId();
        Optional<Template> templateOptional = templateRepository.findById(templateId);
        if (!templateOptional.isPresent()) {
            return new ApiResponse("Bunday id dagi template mavjud emas", false);
        }
        Template template = templateOptional.get();

        Seat seat = new Seat();
        seat.setPrice(seatDto.getPrice());
        seat.setBookedDate(seatDto.getBookedDate());
        seat.setName(seatDto.getName());
        seat.setExpireDate(seatDto.getExpireDate());
        seat.setStatus(seatDto.getStatus());
        seat.setRaw(seatDto.getRaw());
        seat.setEvent(event);
        seat.setTemplate(template);

        seatRepository.save(seat);

        return new ApiResponse("Succesfully added", true);
    }

    public ApiResponse update(Integer id , SeatDto seatDto) {
        //Event
        Integer eventId = seatDto.getEventId();
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (!eventOptional.isPresent()) {
            return new ApiResponse("Bunday id dagi event mavjud emas", false);
        }
        Event event = eventOptional.get();

        Integer templateId = seatDto.getTemplateId();
        Optional<Template> templateOptional = templateRepository.findById(templateId);
        if (!templateOptional.isPresent()) {
            return new ApiResponse("Bunday id dagi template mavjud emas", false);
        }
        Template template = templateOptional.get();

        Optional<Seat> seatOptional = seatRepository.findById(id);
        if (!seatOptional.isPresent()) {
            return new ApiResponse("Bunday id dagi seat mavjud emas", false);
        }

        Seat seat = seatOptional.get();
        seat.setPrice(seatDto.getPrice());
        seat.setBookedDate(seatDto.getBookedDate());
        seat.setName(seatDto.getName());
        seat.setExpireDate(seatDto.getExpireDate());
        seat.setStatus(seatDto.getStatus());
        seat.setRaw(seatDto.getRaw());
        seat.setEvent(event);
        seat.setTemplate(template);

        seatRepository.save(seat);

        return new ApiResponse("Succesfully updated", true);
    }

    public ApiResponse deleted(Integer id) {
        Optional<Seat> seatOptional = seatRepository.findById(id);
        if (!seatOptional.isPresent()) {
            return new ApiResponse("Bunday id dagi seat mavjud emas", false);
        }
        seatRepository.deleteById(id);

        return new ApiResponse("Succes fully deleted", true);
    }
}
