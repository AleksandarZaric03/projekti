package com.example.demo.Model;

import com.example.demo.DTO.AccountDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Account(Long id, String password, List<Review> reviews, String username) {

        this.id = id;
        this.password = password;
        this.reviews = reviews;
        this.username = username;
    }

    public Account() {
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountDTO toDto(){
        return new AccountDTO(this.id,this.password,this.username);
    }
}
