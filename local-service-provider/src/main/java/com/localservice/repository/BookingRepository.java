package com.localservice.repository;

import com.localservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId")
    List<Booking> findByUserId(@Param("userId") Long userId);

    @Query("SELECT b FROM Booking b WHERE b.service.provider.id = :providerId")
    List<Booking> findByProviderId(@Param("providerId") Long providerId);

    @Query("SELECT b FROM Booking b WHERE b.status = :status")
    List<Booking> findByStatus(@Param("status") Booking.Status status);
}
