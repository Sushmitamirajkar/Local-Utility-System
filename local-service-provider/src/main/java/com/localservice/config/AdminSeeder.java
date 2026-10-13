package com.localservice.config;

import com.localservice.model.User;
import com.localservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(@NonNull String... args) {
        if (!userRepository.existsByEmail("sanjaymuddebihal99@company.com")) {
            User admin = User.builder()
                    .name("Sanjay")
                    .email("sanjaymuddebihal99@company.com")
                    .password(passwordEncoder.encode("Sanjay@8792"))
                    .phone("9999999999")
                    .address("Admin Office")
                    .role(User.Role.ADMIN)
                    .build();
            userRepository.save(admin);
            System.out.println("Admin user created: sanjaymuddebihal99@company.com / Sanjay@8792");
        }
    }
}
