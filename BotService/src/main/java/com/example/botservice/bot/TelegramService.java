package com.example.botservice.bot;


import org.hibernate.sql.Update;

public interface TelegramService {

    SendMessage menu(Update update);

    SendMessage order(Update update);

    SendMessage selectCategory(Update update);

    SendMessage selectEvent(Update update);

    SendMessage myOrders(Update update);

    SendMessage settings(Update update);

    SendMessage aboutUs(Update update);

    SendMessage comment(Update update);

    SendMessage sendMessageWithEvent(Update update);

    SendMessage connection(Update update);

    SendMessage shareContact(Update update);


    SendMessage enterFullName(Update update);

    SendMessage enterCompanyName(Update update);

    SendMessage enterPosition(Update update);

    SendMessage enterRegister(Update update);
}
