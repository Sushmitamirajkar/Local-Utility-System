package com.localservice.controller;

import com.localservice.dto.DTOs.*;
import com.localservice.model.Booking;
import com.localservice.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) { this.bookingService = bookingService; }

    @PostMapping("/user/bookings")
    public ResponseEntity<Booking> book(@RequestBody BookingRequest req, Principal principal) {
        return ResponseEntity.ok(bookingService.createBooking(req, principal.getName()));
    }

    @GetMapping("/user/bookings")
    public ResponseEntity<List<Booking>> getUserBookings(Principal principal) {
        return ResponseEntity.ok(bookingService.getUserBookings(principal.getName()));
    }

    @GetMapping("/provider/bookings")
    public ResponseEntity<List<Booking>> getProviderBookings(Principal principal) {
        return ResponseEntity.ok(bookingService.getProviderBookings(principal.getName()));
    }

    @PutMapping("/bookings/{id}/status")
    public ResponseEntity<Booking> updateStatus(@PathVariable Long id,
                                                 @RequestBody BookingStatusRequest req,
                                                 Principal principal) {
        return ResponseEntity.ok(bookingService.updateStatus(id, req.getStatus(), principal.getName()));
    }
}
