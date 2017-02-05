package com.bla.services.INTERFACES;

import com.bla.entities.Review;

import java.util.List;

public interface ReviewsService {

    Review addReview(Review review);

    void update(Review review);

    List<Review> findAll();

    Review findById(int id);

}
