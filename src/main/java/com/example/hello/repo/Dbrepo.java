package com.example.hello.repo;

import com.example.hello.model.Kusers;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class Dbrepo {
    private JdbcTemplate jdbcTemplate;

    public void saveReq(String chatid, String username, String firstname, String lastname, String request) {
        String sql = String.format("insert into t_users(chatid, username, firstname, lastname,request) values('%s','%s','%s','%s','%s')",
                chatid, username, firstname, lastname, request);
        jdbcTemplate.update(sql);
    }

    public List<Kusers> getAllUsersChatId() {
        String sql = "select distinct(chatid) from t_users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Kusers.class));
    }
}
