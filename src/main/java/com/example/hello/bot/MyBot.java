package com.example.hello.bot;


import com.example.hello.model.Kusers;
import com.example.hello.service.Dbservice;
import com.example.hello.service.Fetcher2Service;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@AllArgsConstructor
public class MyBot extends TelegramLongPollingBot {
    private Fetcher2Service fetcher;
    private Dbservice dbservice;

    @Override
    public String getBotUsername() {
        return "kitobqidirishbot";
    }

    @Override
    public String getBotToken() {
        return "5199910354:AAH5psM8Gk-wx_Fos_dnIGB8LBvAbwl0clU";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            Message message = update.getMessage();
            String chatId = String.valueOf(message.getChatId());
            String myid = "542680353";
            if (message.hasText()){
                String text = message.getText();
                if (chatId.equals(myid)) {
                    if (text.startsWith("send:")) {
                        List<Kusers> list = dbservice.getAllChatIds();
                        list.stream()
                                .map(Kusers::getChatId)
                                .forEach(s -> sendMessage(s, text.split(":")[1]));
                    } else {
                        perform(chatId, text);
                    }
                }else{
                    if (!text.equals("/start")){
                        if (text.length() < 100) {
                            dbservice.saveReq(message);
                            sendMessage("542680353", message.getChat().getUserName() + ":" + text);
                            perform(chatId, text);
                        }   else{
                            sendMessage(chatId,"So'rov hajmi 100 tadan ko'p bo'lmasligi kerak!!!");
                            sendMessage(chatId,"Kitob nomini kiriting");
                        }
                    }else{
                        sendMessage(chatId,"Kitob nomini kiriting");
                    }
                }

            }else{
                deleteMessage(chatId,message.getMessageId());
                sendMessage(chatId,"Qidirish uchun kitob nomini kiriting");
            }
        }
    }


    void perform(String chatId, String text) {
        Mono<List<String>> urls = fetcher.fetch(text);
        urls.subscribe(urls1 -> {
            if (urls1.size() != 0) {
                for (String url : urls1) {
                    sendMessage(chatId, url);
                }
            } else {
                sendMessage(chatId, "Hech narsa topilmadi");
                sendMessage(chatId, "Kitob nomini kiriting");
            }
        });

    }

    @SneakyThrows
    public void sendMessage(String chat_id, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        message.setText(text);
        execute(message);

    }
    @SneakyThrows
    public void deleteMessage(String chatid, Integer messageid){
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatid);
        deleteMessage.setMessageId(messageid);
        execute(deleteMessage);
    }
}
