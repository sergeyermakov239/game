package com.example.game;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {
    public static final String TOKEN = "5056913305:AAGIe3Fi25jLQWF-2pCwgTnh_Wx__EyFa_A";
    public static final String USERNAME = "BestWeather239bot";
    public int t=0;      //на какой команде я сейчас нахожусь


    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            SendMessage sendMessage = new SendMessage();
            Long chatId = update.getMessage().getChatId();
            String message = String.valueOf(update.getMessage().getText());
            sendMessage.setChatId(String.valueOf(chatId));
            sendMessage.setText("Введите количество цифр в строчке");
            execute(sendMessage);


            SendMessage sendMessage1 = new SendMessage();
            Long chatId1 = update.getMessage().getChatId();
            String message1 = "Введите количество цифр в строчке";
            sendMessage1.setChatId(String.valueOf(chatId1));
            sendMessage1.setText(message1);
            execute(sendMessage1);
            message1 = String.valueOf(update.getMessage().getText());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
