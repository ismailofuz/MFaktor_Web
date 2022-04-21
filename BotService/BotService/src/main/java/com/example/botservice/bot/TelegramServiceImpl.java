package com.example.botservice.bot;


import com.example.botservice.entity.User;
import com.example.botservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class TelegramServiceImpl implements TelegramService {

    static String chatId = null;

    @Autowired
    UserRepository userRepository;


    @Override
    public SendMessage shareContact(Update update) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton("Raqamni jo'natish");
        button.setRequestContact(true);
        row.add(button);
        keyboardRowList.add(row);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return SendMessage.builder()
                .text("Xush kelibsiz !\nTelefon raqamingizni yuboring.").chatId(String.valueOf(update.getMessage().getChatId())).replyMarkup(replyKeyboardMarkup).build();
    }


    @Override
    public SendMessage enterPosition(Update update) {
        return SendMessage.builder()
                .text("Lavozimingizni kiriting: ").chatId(String.valueOf(update.getMessage().getChatId())).build();
    }

    @Override
    public SendMessage enterRegister(Update update) {
        Optional<User> optionalUser = userRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
        User user = optionalUser.get();

        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);

        List<KeyboardRow> keyboardRowList=new ArrayList<>();
        KeyboardRow row1=new KeyboardRow();
        KeyboardRow row2=new KeyboardRow();

        KeyboardButton button1=new KeyboardButton(Constant.REGISTER);
        KeyboardButton button2=new KeyboardButton(Constant.EDIT_INFORM);


        row1.add(button1);
        row2.add(button2);

//        keyboardRowList.addAll(Arrays.asList(row1,row2));
        keyboardRowList.add(row1);
        keyboardRowList.add(row2);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        return SendMessage.builder()
                .text("Telefon nomer :\n" + user.getPhoneNumber() + "\n\n" +
                        "FIO :\n" + user.getFullName() + "\n\n" +
                        "Korxona nomi :\n" + user.getCompanyName() + "\n\n" +
                        "Lavozimi :\n"+user.getPosition()
                )
                .chatId(String.valueOf(update.getMessage().getChatId())).replyMarkup(replyKeyboardMarkup).build();
    }

    @Override
    public SendMessage enterCompanyName(Update update) {
        return SendMessage.builder()
                .text("Korxona nomini kiriting: ").chatId(String.valueOf(update.getMessage().getChatId())).build();
    }

    @Override
    public SendMessage enterFullName(Update update) {
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        replyKeyboardRemove.setRemoveKeyboard(true);
        return SendMessage.builder()
                .text("Ism Familiyangizni kiriting.").chatId(String.valueOf(update.getMessage().getChatId())).replyMarkup(replyKeyboardRemove).build();
    }

    @Override
    public SendMessage menu(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        Optional<User> byChatId = userRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
        User user = byChatId.get();
        sendMessage.setText("Salom " + user.getFullName() );

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();

        KeyboardButton button = new KeyboardButton(Constant.CLOSEST_EVENTS);
        KeyboardButton button1 = new KeyboardButton(Constant.EVENTS);
        KeyboardButton button2 = new KeyboardButton(Constant.CONNECTION);
        KeyboardButton button3 = new KeyboardButton(Constant.SETTINGS);

        row.add(button);
        row1.add(button1);
        row2.add(button2);
        row2.add(button3);

        keyboardRowList.add(row);
        keyboardRowList.add(row1);
        keyboardRowList.add(row2);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }

    @Override
    public SendMessage order(Update update) {
        return null;
    }

    @Override
    public SendMessage selectCategory(Update update) {
        return null;
    }

    @Override
    public SendMessage selectEvent(Update update) {
        return null;
    }

    @Override
    public SendMessage myOrders(Update update) {
        return null;
    }

    @Override
    public SendMessage settings(Update update) {
        return null;
    }

    @Override
    public SendMessage aboutUs(Update update) {
        return null;
    }

    @Override
    public SendMessage comment(Update update) {
        return null;
    }

    @Override
    public SendMessage sendMessageWithEvent(Update update) {
        return null;
    }

    @Override
    public SendMessage connection(Update update) {
        return null;
    }
}
