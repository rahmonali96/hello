package com.example.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Developed by Rahmonali Yoqubov
 * Email: rahmonaliyoqubov@gmail.com
 * Date: 04.08.2022
 * Project: hello
 */
@Controller
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "index";
    }
}
