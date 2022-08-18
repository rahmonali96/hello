package com.example.hello.repo;


import com.example.hello.model.History;
import com.example.hello.model.Kusers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Dbrepo extends JpaRepository<History, Long> {
    @Query(value = "select distinct h.chatId from History h")
    List<Kusers> findAllChatIds();
}
