package com.localservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean active = true;

    public enum Role { USER, PROVIDER, ADMIN }

    public User() {}

    public User(Long id, String name, String email, String password, String phone, String address, Role role, boolean active) {
        this.id = id; this.name = name; this.email = email; this.password = password;
        this.phone = phone; this.address = address; this.role = role; this.active = active;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id; private String name; private String email; private String password;
        private String phone; private String address; private Role role; private boolean active = true;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder phone(String phone) { this.phone = phone; return this; }
        public Builder address(String address) { this.address = address; return this; }
        public Builder role(Role role) { this.role = role; return this; }
        public Builder active(boolean active) { this.active = active; return this; }
        public User build() { return new User(id, name, email, password, phone, address, role, active); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
