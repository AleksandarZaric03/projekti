package com.example.demo.Model;

import com.example.demo.DTO.CityDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public City( Long id, String name, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.reviews = reviews;
    }

    public City() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public CityDTO toDto(){
        return new CityDTO(this.id, this.name);
    }
}
