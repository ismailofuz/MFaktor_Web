package com.example.adminservice.service;

import com.example.adminservice.entity.Attachment;
import com.example.adminservice.entity.Event;
import com.example.adminservice.entity.Speaker;
import com.example.adminservice.payload.ApiResponse;
import com.example.adminservice.payload.EventDto;
import com.example.adminservice.repository.AttachmentRepository;
import com.example.adminservice.repository.EventRepository;
import com.example.adminservice.repository.SeatRepository;
import com.example.adminservice.repository.SpeakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    final EventRepository eventRepository;
    final AttachmentRepository attachmentRepository;
    final SpeakerRepository speakerRepository;

    public ApiResponse add(EventDto eventDto) {

        Integer speakerId = eventDto.getSpeakerId();
        Optional<Speaker> speakerOptional = speakerRepository.findById(speakerId);
        if (!speakerOptional.isPresent()){
            return new ApiResponse("There is no speaker in such an id", false);
        }
        Speaker speaker = speakerOptional.get();

        Integer attachmentId = eventDto.getAttachmentId();
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(attachmentId);
        Attachment attachment = attachmentOptional.get();

        String description = eventDto.getDescription();
        String name = eventDto.getName();
        Timestamp startTime = eventDto.getStartTime();
        Boolean byPlace = eventDto.getByPlace();

        Event event = new Event();
        event.setSpeaker(speaker);
        event.setAttachment(attachment);
        event.setDescription(description);
        event.setName(name);
        event.setStartTime(startTime);
        event.setByPlace(byPlace);

        eventRepository.save(event);

        return new ApiResponse("Succesfully added", true);
    }
}
