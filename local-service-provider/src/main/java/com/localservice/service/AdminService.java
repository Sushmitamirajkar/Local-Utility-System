package com.localservice.service;

import com.localservice.model.User;
import com.localservice.repository.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final BookingRepository bookingRepository;

    public AdminService(UserRepository userRepository, ServiceRepository serviceRepository,
                        BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.bookingRepository = bookingRepository;
    }

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.findByRole(User.Role.USER).size());
        stats.put("totalProviders", userRepository.findByRole(User.Role.PROVIDER).size());
        stats.put("totalServices", serviceRepository.count());
        stats.put("totalBookings", bookingRepository.count());
        stats.put("pendingBookings", bookingRepository.findByStatus(com.localservice.model.Booking.Status.PENDING).size());
        return stats;
    }

    public List<User> getAllUsers() { return userRepository.findByRole(User.Role.USER); }

    public List<User> getAllProviders() { return userRepository.findByRole(User.Role.PROVIDER); }

    public void toggleUserStatus(@NonNull Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(!user.isActive());
        userRepository.save(user);
    }

    public void deleteUser(@NonNull Long userId) {
        userRepository.deleteById(userId);
    }
}
