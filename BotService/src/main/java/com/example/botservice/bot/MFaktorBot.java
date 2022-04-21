package com.example.botservice.bot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MFaktorBot extends  {

    final UserRepository userRepository;


    final TelegramServiceImpl telegramService;
    @Value("${telegram.bot.username}")
    String username;
    @Value("${telegram.bot.botToken}")
    String botToken;

    @Override
    public String getBotToken() {
        return "5378994767:AAFA5ZXcSnjU1_Mmg4v6XCbzVdysMZbEWVc";
    }

    @Override
    public String getBotUsername() {
        return "MFakt0rBot";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        User currentUser;
        if (update.hasMessage()) {
            Optional<User> optionalUser = userRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));

            Message message = update.getMessage();
            if (message.hasText()) {
                if (message.getText().equals("/start")) {

                    if (optionalUser.isPresent()) {
                        currentUser = optionalUser.get();
                        currentUser.setState(State.START);
//                        currentUser.setFullName(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName());
                        userRepository.save(currentUser);
                    } else {
                        currentUser = new User();
                        currentUser.setChatId(String.valueOf(update.getMessage().getChatId()));
                        currentUser.setState(State.START);
                        userRepository.save(currentUser);
                    }
                    execute(telegramService.shareContact(update));
//                    execute(telegramService.welcome(update));
                } else {
                    currentUser = optionalUser.get();
                    switch (currentUser.getState()) {
                        case State.ENTER_FULLNAME:
                            currentUser.setState(State.ENTER_COMPANY_NAME);
                            currentUser.setFullName(update.getMessage().getText());
                            userRepository.save(currentUser);
                            execute(telegramService.enterCompanyName(update));
                            break;

                        case State.ENTER_COMPANY_NAME:
                            currentUser.setState(State.ENTER_POSITION);
                            currentUser.setCompanyName(update.getMessage().getText());
                            userRepository.save(currentUser);
                            execute(telegramService.enterPosition(update));
                            break;
                        case State.ENTER_POSITION:
                            currentUser.setState(State.ENTER_REGISTER);
                            currentUser.setPosition(update.getMessage().getText());
                            userRepository.save(currentUser);
                            execute(telegramService.enterRegister(update));
                            break;
                        case State.ENTER_REGISTER:
                            if(update.getMessage().getText().equals(Constant.REGISTER)) {
                                currentUser.setState(State.MENU);
                                userRepository.save(currentUser);
                                execute(telegramService.menu(update));
                            }else if(update.getMessage().getText().equals(Constant.EDIT_INFORM)){
                                currentUser.setState(State.START);
                                userRepository.save(currentUser);
                                execute(telegramService.shareContact(update));
                            }else {
                                currentUser.setState(State.ENTER_FULLNAME);
                                userRepository.save(currentUser);
                                execute(telegramService.enterFullName(update));
                            }
                            break;

                        case State.START:
                            switch (update.getMessage().getText()) {
                                case Constant.CLOSEST_EVENTS:
                                    execute(telegramService.selectEvent(update));
                                    break;
                                case Constant.EVENTS:
                                    execute(telegramService.selectEvent(update));
                                    break;
                                case Constant.SETTINGS:
                                    execute(telegramService.settings(update));
                                    break;
                                case Constant.CONNECTION:
                                    execute(telegramService.connection(update));
                                    break;
                            }
                            break;


                    }
                }
            }
            else if (message.hasContact()){
                currentUser=optionalUser.get();
                String phoneNumber = update.getMessage().getContact().getPhoneNumber();
                if (userRepository.existsByPhone(phoneNumber)) {
                    currentUser.setState(State.MENU);
                    userRepository.save(currentUser);
                    execute(telegramService.menu(update));
                }else {
                    currentUser.setState(State.ENTER_FULLNAME);
                    currentUser.setPhone(phoneNumber);
                    userRepository.save(currentUser);
                    execute(telegramService.enterFullName(update));
                }
            }
        }


    }
}
