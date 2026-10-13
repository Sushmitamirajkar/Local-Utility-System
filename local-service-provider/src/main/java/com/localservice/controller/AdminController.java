package com.localservice.controller;

import com.localservice.model.*;
import com.localservice.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;
    private final BookingService bookingService;

    public AdminController(AdminService adminService, BookingService bookingService) {
        this.adminService = adminService;
        this.bookingService = bookingService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> dashboard() {
        return ResponseEntity.ok(adminService.getDashboardStats());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/providers")
    public ResponseEntity<List<User>> getProviders() {
        return ResponseEntity.ok(adminService.getAllProviders());
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PutMapping("/users/{id}/toggle")
    public ResponseEntity<Void> toggleUser(@PathVariable Long id) {
        adminService.toggleUserStatus(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
