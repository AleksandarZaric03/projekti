package com.example.demo.Service;

import com.example.demo.Model.Review;
import com.example.demo.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public  Iterable<Review> findAll(){
        return reviewRepository.findAll();
    }

    public Optional<Review> findById(Long id){

        return reviewRepository.findById(id);
    }

    public Review save(Review review){

        return reviewRepository.save(review);
    }

    public void delete(Review review){

        reviewRepository.delete(review);
    }

    public void delete(Long id){

        reviewRepository.deleteById(id);
    }
}
