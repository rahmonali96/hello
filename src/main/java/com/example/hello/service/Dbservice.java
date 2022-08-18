package com.example.hello.service;

import com.example.hello.model.History;
import com.example.hello.model.Kusers;
import com.example.hello.repo.Dbrepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Service
@AllArgsConstructor
public class Dbservice {
    private Dbrepo dbrepo;
    public void saveReq(Message message) {
        String chatid = String.valueOf(message.getChatId());
        Chat chat = message.getChat();
        String username = chat.getUserName() == null ? "" : chat.getUserName();
        String firstname = chat.getFirstName() == null ? "" : chat.getFirstName();
        String lastname = chat.getLastName() == null ? "" : chat.getLastName();
        String request = message.getText().replace("'","''");
        dbrepo.save(History.builder()
                        .chatId(chatid)
                        .username(username)
                        .firstname(firstname)
                        .lastname(lastname)
                        .request(request)
                .build());
    }

    public List<Kusers> getAllChatIds() {
        return dbrepo.findAllChatIds();
    }
}
