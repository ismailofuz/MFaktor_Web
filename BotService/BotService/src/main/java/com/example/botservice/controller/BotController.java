package com.example.botservice.controller;

import com.example.botservice.bot.MFaktorBot;
import com.example.botservice.bot.TelegramServiceImpl;
import com.example.botservice.entity.Event;
import com.example.botservice.entity.User;
import com.example.botservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class BotController {

    final TelegramServiceImpl telegramService;
    final MFaktorBot mFaktorBot;
    final UserRepository userRepository;

    @SneakyThrows
    @PostMapping("/sendMessageUsers")
    public void setAllMessage(@RequestBody Event event) {
        //
        List<User> userList = userRepository.findAllByChatIdNotNull();
        List<String> listChatId=new ArrayList<>();
        for (User user : userList) {
            listChatId.add(user.getChatId());
        }
        List<SendMessage> sendMessageList = telegramService.sendMessageUsers(event, listChatId);
        for (SendMessage sendMessage : sendMessageList) {
//            mFaktorBot.execute(sendMessage);
mFaktorBot.execute(sendMessage);
        }
    }

}
