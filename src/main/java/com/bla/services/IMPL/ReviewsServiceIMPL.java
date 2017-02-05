package com.bla.services.IMPL;

import com.bla.entities.Review;
import com.bla.repositories.ReviewsRepository;
import com.bla.services.INTERFACES.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewsServiceIMPL implements ReviewsService{

    @Autowired
    ReviewsRepository reviewsRepository;

    @Override
    public Review addReview(Review review) {
        return reviewsRepository.save(review);
    }

    @Override
    public void update(Review review) {
        reviewsRepository.save(review);
    }

    @Override
    public List<Review> findAll() {
        return reviewsRepository.findAll();
    }

    @Override
    public Review findById(int id) {
        return reviewsRepository.findOne(id);
    }
}
