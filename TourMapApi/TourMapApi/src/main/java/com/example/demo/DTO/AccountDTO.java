package com.example.demo.DTO;

import com.example.demo.Model.Account;

import com.example.demo.Model.Review;

import java.util.ArrayList;

public class AccountDTO {

    private Long id;

    private String username;

    private String password;



    public AccountDTO(Long id, String password, String username) {

        this.id = id;
        this.password = password;
        this.username = username;
    }

    public AccountDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Account toEntity(){
        return new Account(this.id, this.password, new ArrayList<Review>(), this.username);
    }
}
