package com.example.demo.DTO;

import com.example.demo.Model.Account;
import com.example.demo.Model.City;
import com.example.demo.Model.Review;

import java.util.ArrayList;

public class CityDTO {

    private Long id;

    private String naziv;

    public CityDTO(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public CityDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public City toEntity(){
        return new City(this.id, this.naziv, new ArrayList<Review>());
    }
}
