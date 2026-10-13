package com.localservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_seq")
    @SequenceGenerator(name = "service_seq", sequenceName = "SERVICE_SEQ", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String category;
    private Double price;
    private String location;

    @Column(nullable = false)
    private boolean available = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id", nullable = false)
    private User provider;

    public Service() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id; private String name; private String description;
        private String category; private Double price; private String location;
        private boolean available = true; private User provider;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder category(String category) { this.category = category; return this; }
        public Builder price(Double price) { this.price = price; return this; }
        public Builder location(String location) { this.location = location; return this; }
        public Builder available(boolean available) { this.available = available; return this; }
        public Builder provider(User provider) { this.provider = provider; return this; }
        public Service build() {
            Service s = new Service();
            s.id = id; s.name = name; s.description = description; s.category = category;
            s.price = price; s.location = location; s.available = available; s.provider = provider;
            return s;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public User getProvider() { return provider; }
    public void setProvider(User provider) { this.provider = provider; }
}
