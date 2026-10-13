package com.localservice.service;

import com.localservice.dto.DTOs.BookingRequest;
import com.localservice.model.*;
import com.localservice.repository.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, ServiceRepository serviceRepository,
                          UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    public Booking createBooking(@NonNull BookingRequest req, @NonNull String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        com.localservice.model.Service service = serviceRepository.findById(req.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        Booking booking = Booking.builder()
                .user(user)
                .service(service)
                .bookingDate(req.getBookingDate())
                .notes(req.getNotes())
                .build();

        return bookingRepository.save(booking);
    }

    public List<Booking> getUserBookings(@NonNull String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findByUserId(user.getId());
    }

    public List<Booking> getProviderBookings(@NonNull String email) {
        User provider = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Provider not found"));
        return bookingRepository.findByProviderId(provider.getId());
    }

    public Booking updateStatus(@NonNull Long bookingId, @NonNull Booking.Status status, @NonNull String email) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        boolean isProvider = booking.getService().getProvider().getEmail().equals(email);
        boolean isUser = booking.getUser().getEmail().equals(email);

        if (!isProvider && !isUser)
            throw new RuntimeException("Unauthorized");

        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() { return bookingRepository.findAll(); }
}
