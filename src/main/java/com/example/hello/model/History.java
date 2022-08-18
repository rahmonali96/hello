package com.example.hello.model;

import lombok.*;

import javax.persistence.*;

/**
 * Developed by Rahmonali Yoqubov
 * Email: rahmonaliyoqubov@gmail.com
 * Date: 18.08.2022
 * Project: kitobqidirishbot
 */
@Entity
@Table(name = "history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String chatId;
    private String username;
    private String firstname;
    private String lastname;
    private String request;
}
