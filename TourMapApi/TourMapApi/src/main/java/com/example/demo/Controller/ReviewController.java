package com.example.demo.Controller;

import com.example.demo.DTO.CityDTO;
import com.example.demo.DTO.ReviewDTO;
import com.example.demo.Model.City;
import com.example.demo.Model.Review;
import com.example.demo.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/recenzije")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(path = "",method = RequestMethod.GET)
    public ResponseEntity<List<ReviewDTO>> getAllReviews(){
        List<ReviewDTO> recenzije = new ArrayList<ReviewDTO>();

        for(Review k : reviewService.findAll()){
            recenzije.add(k.toDto());
        }

        return new ResponseEntity<List<ReviewDTO>>(recenzije, HttpStatus.OK);
    }

    @RequestMapping(path = "",method = RequestMethod.POST)
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO novaRecenzija){

        Review rezultat = reviewService.save(novaRecenzija.toEntity());
        if(rezultat != null){
            return new ResponseEntity<ReviewDTO>(rezultat.toDto(),HttpStatus.CREATED);
        }

        return new ResponseEntity<ReviewDTO>(HttpStatus.OK);
    }

    @RequestMapping(path ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReviewDTO> getReview(@PathVariable("id") Long id){

        Optional<Review> recenzija = reviewService.findById(id);

        if(recenzija.isPresent()){
            return new ResponseEntity<ReviewDTO>(recenzija.get().toDto(),HttpStatus.OK);
        }
        return new ResponseEntity<ReviewDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path ="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable("id") Long id, @RequestBody ReviewDTO novaRecenzija){
        Optional<Review> recenzija = reviewService.findById(id);

        if(recenzija.isPresent()){
            recenzija.get().setId(id);
            recenzija.get().setReview(novaRecenzija.getReview());
            recenzija.get().setName(novaRecenzija.getName());
            recenzija.get().setCategory(novaRecenzija.getCategory());
            recenzija.get().setLocation(novaRecenzija.getLocation());
            recenzija.get().setCity(novaRecenzija.getCity().toEntity());
            recenzija.get().setAccount(novaRecenzija.getAccount().toEntity());
            reviewService.save(recenzija.get());
            return new ResponseEntity<ReviewDTO>(recenzija.get().toDto(),HttpStatus.OK);
        }
        return new ResponseEntity<ReviewDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<ReviewDTO> deleteReview(@PathVariable("id") Long id){

        Optional<Review> review = reviewService.findById(id);

        if(review.isPresent()){
            reviewService.delete(id);
            return new ResponseEntity<ReviewDTO>(review.get().toDto(),HttpStatus.OK);
        }

        return new ResponseEntity<ReviewDTO>(HttpStatus.NOT_FOUND);
    }
}
