package com.example.game;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

@Component
public class Bot extends TelegramLongPollingBot {
    public static final String TOKEN = "5056913305:AAGIe3Fi25jLQWF-2pCwgTnh_Wx__EyFa_A";
    public static final String USERNAME = "BestWeather239bot";
    public int t=0;      //на какой команде я сейчас нахожусь
    public int t3=1;   //на какой команде я нахожусь в 3 этапе
    ArrayList<Integer> arr=new ArrayList<>();
    int k=0;               //количество нулей
    int n=0;                //оличество цифр в строчке
    int g = 1;     //показывает, кто ходит в данный момент 1-я, 2-компьютер
    int p=0;       //вид хода
    int index;
    int number1;


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
            sendMessage.setChatId(String.valueOf(chatId));
            String message = String.valueOf(update.getMessage().getText());
            if (t==0) {
                sendMessage.setText("Введите количество цифр в строчке");
                execute(sendMessage);
                t=1;
            } else
                if (t==1){
                    n=Integer.parseInt(message);
                    sendMessage.setText("Введите строчку из цифр");
                    execute(sendMessage);
                    t=2;
                } else
                    if (t==2){

                        for (int i =0;i<message.length();i++){
                            if (message.charAt(i)>='0' &&message.charAt(i)<='9'){
                                int a= Integer.parseInt(String.valueOf(message.charAt(i)));
                                if (a==0){
                                    k+=1;
                                };
                                arr.add(a);
                            }
                        };
                        sendMessage.setText("За ход вы можете 1) изменить в меньшую сторону одну из цифр, максимум до 0 (отрицательных величин в игре нет);" +
                                "   2) стереть любой ноль и все цифры справа него, сократив таким образом длину полосы.");
                        execute(sendMessage);
                        sendMessage.setText("Ваш ход; "+" Введите, какой ход вы делаете : 1 или 2");
                        execute(sendMessage);
                        t=3;
                    } else
                        if (t==3){
                            if (t3==0){
                                g=1;
                                sendMessage.setText("Ваш ход; "+" Введите, какой ход вы делаете : 1 или 2");
                                execute(sendMessage);
                                t3=1;
                            } else
                                if (t3==1){
                                    p=Integer.parseInt(message);
                                    if (p==1){
                                        sendMessage.setText("Введите номер цифры, которую вы хотите изменить (нумерация с 0)  и до какого значения вы хотите эту цифру уменьшить ");
                                        execute(sendMessage);
                                    } else if (p==2){
                                        sendMessage.setText("Введите индекс нуля , справа от которого вы хотите стереть цифры ");
                                        execute(sendMessage);
                                    }
                                    t3=2;
                                } else if (t3==2){
                                    if (p==1){
                                        index=Integer.parseInt(String.valueOf(message.charAt(0)));
                                        number1=Integer.parseInt(String.valueOf(message.charAt(2)));
                                        arr.set(index,number1);
                                        if (number1==0){
                                            k+=1;
                                        }
                                    } else {
                                        index=Integer.parseInt(message);
                                        for (int i=arr.size()-1;i>=index;i=i-1){
                                            if (arr.get(i)==0){
                                                k=k-1;
                                            };
                                            arr.remove(i);
                                        }
                                    };
                                    if (k>0){
                                        sendMessage.setText("Теперь строка выглядит так "+arr);
                                        execute(sendMessage);
                                        g = 2;
                                        p = (int)(Math.round(Math.random() + 1));
                                        if (p == 1&&arr.size()>k) {
                                            int b=(int) (Math.random() * (arr.size() - 1));
                                            while (arr.get(b)==0){
                                                b=(int) (Math.random() * (arr.size() - 1));
                                            }
                                            index = b;
                                            int a = (int) (Math.random() * arr.get(index));
                                            if (arr.get(index) != 0) {
                                                if (a == 0) {
                                                    k += 1;
                                                }
                                            }
                                            arr.set(index, a);
                                        } else  {
                                            int index = arr.lastIndexOf(0);
                                            for (int i=arr.size()-1;i>=index;i=i-1) {
                                                if (arr.get(i) == 0) {
                                                    k = k - 1;
                                                }
                                                arr.remove(i);
                                            }
                                        }
                                        sendMessage.setText("; Ход компьютера; После хода компьютера строка выглядит так"+ arr);
                                        execute(sendMessage);
                                    }
                                    if (k==0){
                                        if (g==1){
                                            sendMessage.setText("Компьютер победил");
                                            execute(sendMessage);
                                        } else{
                                            sendMessage.setText("Вы победили!");
                                            execute(sendMessage);
                                        };
                                        sendMessage.setText("Введите количество цифр в строчке");
                                        execute(sendMessage);
                                        t=1;
                                    } else{
                                        g=1;
                                        t=3;
                                        t3=1;
                                        sendMessage.setText("Ваш ход; "+" Введите, какой ход вы делаете : 1 или 2");
                                        execute(sendMessage);
                                    }
                                }

                        }





        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
