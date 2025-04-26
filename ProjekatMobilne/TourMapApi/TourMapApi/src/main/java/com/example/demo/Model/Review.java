package com.example.demo.Model;

import com.example.demo.DTO.ReviewDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;

    private String name;

    private String location;

    private String category;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public Review(Account account, String category, City city, Long id, String location, String name, String review) {
        this.account = account;
        this.category = category;
        this.city = city;
        this.id = id;
        this.location = location;
        this.name = name;
        this.review = review;
    }

    public Review() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ReviewDTO toDto(){
        return new ReviewDTO(this.account.toDto(), this.category, this.city.toDto(), this.id, this.location, this.name, this.review);
    }
}
