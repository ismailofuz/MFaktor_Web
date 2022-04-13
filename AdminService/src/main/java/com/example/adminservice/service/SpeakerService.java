package com.example.adminservice.service;

import com.example.adminservice.entity.Attachment;
import com.example.adminservice.entity.Speaker;
import com.example.adminservice.payload.ApiResponse;
import com.example.adminservice.payload.SpeakerDTO;
import com.example.adminservice.repository.AttachmentRepository;
import com.example.adminservice.repository.SpeakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SpeakerService {

    final SpeakerRepository speakerRepository;
    final AttachmentRepository attachmentRepository;

    public ApiResponse getSpeaker(Integer id) {
        Optional<Speaker> optionalSpeaker = speakerRepository.findById(id);
        if (optionalSpeaker.isPresent()) {
            Speaker speaker = optionalSpeaker.get();
            return new ApiResponse("", true, speaker);
        }
        return new ApiResponse("This speaker not found!", false);
    }

    public ApiResponse add(SpeakerDTO dto) {
        boolean existsByPhoneNumber = speakerRepository.existsByPhoneNumber(dto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new ApiResponse("This phone number already exists!", false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(dto.getAttachmentId());
        if (optionalAttachment.isEmpty()) {
            return new ApiResponse("Attachment content not found, please choose another!", false);
        }
        Attachment attachment = optionalAttachment.get();
        Speaker speaker = new Speaker();
        speaker.setFirstName(dto.getFirstName());
        speaker.setLastName(dto.getLastName());
        speaker.setMiddleName(dto.getMiddleName());
        speaker.setCompanyName(dto.getCompanyName());
        speaker.setPosition(dto.getPosition());
        speaker.setBio(dto.getBio());
        speaker.setPhoneNumber(dto.getPhoneNumber());
        speaker.setAttachment(attachment);
        speakerRepository.save(speaker);
        return new ApiResponse("Successfully added!", true);
    }

    public ApiResponse edit(Integer id, SpeakerDTO dto) {
        Optional<Speaker> optionalSpeaker = speakerRepository.findById(id);
        if (optionalSpeaker.isPresent()) {
            Speaker speakerEdit = optionalSpeaker.get();
            boolean existsByPhoneNumber = speakerRepository.existsByPhoneNumber(dto.getPhoneNumber());
            if (existsByPhoneNumber) {
                return new ApiResponse("This phone number already exists!", false);
            }
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(dto.getAttachmentId());
            if (optionalAttachment.isEmpty()) {
                return new ApiResponse("Attachment content not found, please choose another!", false);
            }
            Attachment attachment = optionalAttachment.get();
            speakerEdit.setFirstName(dto.getFirstName());
            speakerEdit.setLastName(dto.getLastName());
            speakerEdit.setMiddleName(dto.getMiddleName());
            speakerEdit.setCompanyName(dto.getCompanyName());
            speakerEdit.setPosition(dto.getPosition());
            speakerEdit.setBio(dto.getBio());
            speakerEdit.setPhoneNumber(dto.getPhoneNumber());
            speakerEdit.setAttachment(attachment);
            speakerRepository.save(speakerEdit);
        }
        return new ApiResponse("Successfully edited!", true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Speaker> optionalSpeaker = speakerRepository.findById(id);
        if (optionalSpeaker.isPresent()) {
            Speaker speaker = optionalSpeaker.get();
            speakerRepository.deleteById(id);
            return new ApiResponse("Successfully deleted!", true);
        }
        return new ApiResponse("This speaker not found!", false);
    }
}
