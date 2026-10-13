package com.localservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "BOOKING_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    private String notes;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Status { PENDING, ACCEPTED, REJECTED, COMPLETED, CANCELLED }

    public Booking() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id; private User user; private Service service;
        private LocalDateTime bookingDate; private String notes;
        private Status status = Status.PENDING; private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder service(Service service) { this.service = service; return this; }
        public Builder bookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; return this; }
        public Builder notes(String notes) { this.notes = notes; return this; }
        public Builder status(Status status) { this.status = status; return this; }
        public Booking build() {
            Booking b = new Booking();
            b.id = id; b.user = user; b.service = service; b.bookingDate = bookingDate;
            b.notes = notes; b.status = status; b.createdAt = createdAt;
            return b;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Service getService() { return service; }
    public void setService(Service service) { this.service = service; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
