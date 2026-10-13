package com.localservice.repository;

import com.localservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findByRole(@Param("role") User.Role role);
}
