package com.localservice.repository;

import com.localservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.service.id = :serviceId")
    List<Review> findByServiceId(@Param("serviceId") Long serviceId);

    @Query("SELECT r FROM Review r WHERE r.user.id = :userId")
    List<Review> findByUserId(@Param("userId") Long userId);
}
