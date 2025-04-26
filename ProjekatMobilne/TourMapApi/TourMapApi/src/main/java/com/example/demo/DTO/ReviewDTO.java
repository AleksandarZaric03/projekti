package com.example.demo.DTO;

import com.example.demo.Model.Review;

public class ReviewDTO {

    private Long id;

    private String review;

    private String name;

    private String location;

    private String category;

    private CityDTO city;

    private AccountDTO account;

    public ReviewDTO(AccountDTO account, String category, CityDTO city, Long id, String location, String name, String review) {
        this.account = account;
        this.category = category;
        this.city = city;
        this.id = id;
        this.location = location;
        this.name = name;
        this.review = review;
    }

    public ReviewDTO() {
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Review toEntity(){
        return new Review(this.account.toEntity(), this.category, this.city.toEntity(),this.id, this.location, this.name, this.review);
    }
}
