package com.example.demo.Repository;

import com.example.demo.Model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository  extends CrudRepository<Review, Long> {
}
