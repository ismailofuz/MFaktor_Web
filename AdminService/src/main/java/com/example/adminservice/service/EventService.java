package com.example.adminservice.service;

import com.example.adminservice.entity.*;
import com.example.adminservice.feignClient.BotFeignClient;
import com.example.adminservice.feignClient.ClientFeignClient;
import com.example.adminservice.payload.ApiResponse;
import com.example.adminservice.payload.EventDto;
import com.example.adminservice.payload.EventResponse;
import com.example.adminservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final BotFeignClient botFeignClient;
    private final EventRepository eventRepository;
    private final AttachmentRepository attachmentRepository;
    private final SpeakerRepository speakerRepository;
    private final SeatRepository seatRepository;
    private final TemplateRepository templateRepository;
    private final ClientFeignClient clientFeignClient;

    public ApiResponse add(EventDto eventDto) {

        Event event = new Event();
        Integer speakerId = eventDto.getSpeakerId();
        Optional<Speaker> speakerOptional = speakerRepository.findById(speakerId);
        Optional<Template> optionalTemplate = templateRepository.findById(eventDto.getTemplateId());
        if (!optionalTemplate.isPresent()) {
            return new ApiResponse("There is no template in such an id", false);
        }
        if (!speakerOptional.isPresent()) {
            return new ApiResponse("There is no speaker in such an id", false);
        }
        Speaker speaker = speakerOptional.get();
        event.setSpeaker(speaker);

        Event save = eventRepository.save(event);
        Template template = optionalTemplate.get();
        Integer attachmentId = eventDto.getAttachmentId();
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(attachmentId);
        Attachment attachment = attachmentOptional.get();

        String description = eventDto.getDescription();
        String name = eventDto.getName();
        Timestamp startTime = eventDto.getStartTime();
        Boolean byPlace = eventDto.getByPlace();
        save.setAttachment(attachment);
        save.setDescription(description);
        save.setTemplate(template);
        save.setName(name);
        save.setStartTime(startTime);
        save.setByPlace(byPlace);


        List<Seat> allByTemplate_id = seatRepository.findAllByTemplate_Id(template.getId());
        event.setSeats(allByTemplate_id);
        eventRepository.save(save);

        // hamma foydalanuvchiga yuborish kerak
        //tadbirmni botga beryapmz
        //TODO
//        botFeignClient.setAllMessage(event);

        return new ApiResponse("Succesfully added", true);
    }

    public ApiResponse findById(Integer id) {
        return new ApiResponse();
        //TODO qilish kerak
    }

    public ApiResponse findAll() {
        List<Event> all = eventRepository.findAll();
        List<EventResponse> eventDtoList = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            Event event = all.get(i);
            EventResponse eventResponse = new EventResponse();
            eventResponse.setAttachmentId(event.getAttachment().getId());
            eventResponse.setName(event.getName());
            eventResponse.setSpeaker(event.getSpeaker().getFirstName() + " " + event.getSpeaker().getLastName());
            eventResponse.setStartTime(event.getStartTime());
        }
        return new ApiResponse();
    }
}
