package com.localservice.repository;

import com.localservice.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query("SELECT s FROM Service s WHERE s.provider.id = :providerId")
    List<Service> findByProviderId(@Param("providerId") Long providerId);

    @Query("SELECT s FROM Service s WHERE s.category = :category AND s.available = true")
    List<Service> findByCategoryAndAvailableTrue(@Param("category") String category);

    @Query("SELECT s FROM Service s WHERE s.available = true")
    List<Service> findByAvailableTrue();

    @Query("SELECT s FROM Service s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')) AND s.available = true")
    List<Service> findByNameContainingIgnoreCaseAndAvailableTrue(@Param("name") String name);
}
