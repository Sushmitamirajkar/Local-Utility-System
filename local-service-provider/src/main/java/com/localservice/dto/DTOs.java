package com.localservice.dto;

import com.localservice.model.Booking;
import com.localservice.model.User;
import java.time.LocalDateTime;

public class DTOs {

    public static class RegisterRequest {
        private String name, email, password, phone, address;
        private User.Role role;
        public String getName() { return name; } public void setName(String name) { this.name = name; }
        public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; } public void setPassword(String password) { this.password = password; }
        public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
        public String getAddress() { return address; } public void setAddress(String address) { this.address = address; }
        public User.Role getRole() { return role; } public void setRole(User.Role role) { this.role = role; }
    }

    public static class LoginRequest {
        private String email, password;
        public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; } public void setPassword(String password) { this.password = password; }
    }

    public static class AuthResponse {
        private String token, role, name;
        private Long id;
        public AuthResponse(String token, String role, String name, Long id) {
            this.token = token; this.role = role; this.name = name; this.id = id;
        }
        public String getToken() { return token; }
        public String getRole() { return role; }
        public String getName() { return name; }
        public Long getId() { return id; }
    }

    public static class ServiceRequest {
        private String name, description, category, location;
        private Double price;
        public String getName() { return name; } public void setName(String name) { this.name = name; }
        public String getDescription() { return description; } public void setDescription(String d) { this.description = d; }
        public String getCategory() { return category; } public void setCategory(String c) { this.category = c; }
        public Double getPrice() { return price; } public void setPrice(Double price) { this.price = price; }
        public String getLocation() { return location; } public void setLocation(String l) { this.location = l; }
    }

    public static class BookingRequest {
        private Long serviceId;
        private LocalDateTime bookingDate;
        private String notes;
        public Long getServiceId() { return serviceId; } public void setServiceId(Long s) { this.serviceId = s; }
        public LocalDateTime getBookingDate() { return bookingDate; } public void setBookingDate(LocalDateTime d) { this.bookingDate = d; }
        public String getNotes() { return notes; } public void setNotes(String notes) { this.notes = notes; }
    }

    public static class BookingStatusRequest {
        private Booking.Status status;
        public Booking.Status getStatus() { return status; } public void setStatus(Booking.Status s) { this.status = s; }
    }

    public static class ReviewRequest {
        private Long serviceId;
        private Integer rating;
        private String comment;
        public Long getServiceId() { return serviceId; } public void setServiceId(Long s) { this.serviceId = s; }
        public Integer getRating() { return rating; } public void setRating(Integer r) { this.rating = r; }
        public String getComment() { return comment; } public void setComment(String c) { this.comment = c; }
    }
}
