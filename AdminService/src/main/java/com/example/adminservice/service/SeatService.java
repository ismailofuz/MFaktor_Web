package com.example.adminservice.service;

import com.example.adminservice.entity.*;
import com.example.adminservice.entity.enums.Status;
import com.example.adminservice.feignClient.ClientFeignClient;
import com.example.adminservice.payload.ApiResponse;
import com.example.adminservice.payload.EventInfoResp;
import com.example.adminservice.payload.EventSeatResp;
import com.example.adminservice.payload.SeatDto;
import com.example.adminservice.repository.EventRepository;
import com.example.adminservice.repository.SeatRepository;
import com.example.adminservice.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final EventRepository eventRepository;
    private final TemplateRepository templateRepository;
    private final ClientFeignClient clientFeignClient;


    public ApiResponse getAll() {

        return new ApiResponse("Seates list", true, seatRepository.findAll());
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
        seatDto.setEventId(seat.getId());
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
        seat.setTemplate(template);

        seatRepository.save(seat);

        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse update(Integer id, SeatDto seatDto) {
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
            return new ApiResponse("Bunday id dagi stul mavjud emas", false);
        }

        Seat seat = seatOptional.get();
        seat.setPrice(seatDto.getPrice());
        seat.setBookedDate(seatDto.getBookedDate());
        seat.setName(seatDto.getName());
        seat.setExpireDate(seatDto.getExpireDate());
        seat.setStatus(seatDto.getStatus());
        seat.setRaw(seatDto.getRaw());
        seat.setTemplate(template);

        seatRepository.save(seat);

        return new ApiResponse("Successfully updated", true);
    }

    public ApiResponse deleted(Integer id) {
        Optional<Seat> seatOptional = seatRepository.findById(id);
        if (!seatOptional.isPresent()) {
            return new ApiResponse("Bunday id dagi stul mavjud emas", false);
        }
        seatRepository.deleteById(id);

        return new ApiResponse("Successfully deleted", true);
    }

//    public ApiResponse getSeatsByEvent(Integer id) {
//        return new ApiResponse("All seats by event",true,seatRepository.findAllByEvent_Id(id));
//    }

    public ApiResponse registerVisitor(Integer eventId, Integer seatId, EventSeatResp eventSeatResp) {

        Event event = eventRepository.getById(eventId);

        Seat seat = seatRepository.getById(seatId);

        String phoneNumber = eventSeatResp.getPhoneNumber();


        return null;
    }

    public ApiResponse sell(Long userId, Integer eventId, Integer seatId, EventSeatResp eventSeatResp) {
        ApiResponse apiResponse = clientFeignClient.checkVisitor(eventSeatResp.getPhoneNumber());
        Optional<Seat> optionalSeat = seatRepository.findById(seatId);
        Seat responseSeat = null;

        if (apiResponse.isSuccess()) {
            Seat seat = optionalSeat.get();
            seat.setBookedDate(new Date());
            seat.setVisitor((Visitor) apiResponse.getData());
            //currentUserni aniqlab
            ApiResponse response = clientFeignClient.getById(userId);
            User currentUser = (User) response.getData();

            Optional<Event> byId = eventRepository.findById(eventId);
            if (!byId.isPresent()){
                return new ApiResponse("Event not found",false);
            }
            Event event = byId.get();
            if (currentUser.getBalance() > seat.getPrice()) {
                double balance = currentUser.getBalance() - seat.getPrice();//balance o'zgardi

                event.setSumma(event.getSumma()+seat.getPrice());
                eventRepository.save(event);
                clientFeignClient.changeBalance(balance, userId);
                seat.setStatus(Status.SOLD);
                //bunda sotib olindi
            } else {
                seat.setStatus(Status.BOOKED);
                //shunchaki band qilindi
            }
            responseSeat = seatRepository.save(seat);
        } else {
            //TODO add yo'liga
        }
        return new ApiResponse(responseSeat.getStatus().toString(), true, responseSeat);
    }

    public ApiResponse getInfo(Integer eventId) {
        EventInfoResp info;
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            List<Seat> seatList = event.getSeats();

            int size = event.getSeats().size();
            info = new EventInfoResp();
            info.setAllSeats(size);
            info.setEvent(event);
            info.setSeats(seatList);
            int empty = 0;
            for (Seat seat : seatList) {
                if (seat.getStatus().equals(Status.EMPTY)) {
                    empty++;
                }
            }
// TODO payment qilingandan keyin togirlanadi

            info.setSoldSeats(size - empty);
            info.setEmptySeats(empty);
            info.setSumm(event.getSumma());

        } else {
            return new ApiResponse("not found", false);
        }
        return new ApiResponse("Info",true,info);
    }
}
