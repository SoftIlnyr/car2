package com.bla.repositories;

import com.bla.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by softi on 22.01.2017.
 */
@Repository
public interface ReviewsRepository extends JpaRepository<Review, Integer> {

}
