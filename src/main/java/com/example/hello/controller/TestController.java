package com.example.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.TimeZone;

/**
 * Developed by Rahmonali Yoqubov
 * Email: rahmonaliyoqubov@gmail.com
 * Date: 04.08.2022
 * Project: hello
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public Message test(HttpServletRequest request) {
        return new Message("Your ip: " + request.getRemoteHost()+"\nUser-Agent: "+request.getHeader("User-Agent"));
    }

    private static class Message{
        private String message;

        public Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
