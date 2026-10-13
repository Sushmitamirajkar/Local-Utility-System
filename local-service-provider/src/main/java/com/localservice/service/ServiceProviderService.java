package com.localservice.service;

import com.localservice.dto.DTOs.ServiceRequest;
import com.localservice.model.Service;
import com.localservice.model.User;
import com.localservice.repository.ServiceRepository;
import com.localservice.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceProviderService {

    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    public ServiceProviderService(ServiceRepository serviceRepository, UserRepository userRepository) {
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    public Service createService(@NonNull ServiceRequest req, @NonNull String providerEmail) {
        User provider = userRepository.findByEmail(providerEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Provider not found"));

        Service service = Service.builder()
                .name(req.getName())
                .description(req.getDescription())
                .category(req.getCategory())
                .price(req.getPrice())
                .location(req.getLocation())
                .provider(provider)
                .build();

        return serviceRepository.save(service);
    }

    public List<Service> getAllAvailable() { return serviceRepository.findByAvailableTrue(); }

    public List<Service> getByCategory(@NonNull String category) {
        return serviceRepository.findByCategoryAndAvailableTrue(category);
    }

    public List<Service> search(@NonNull String name) {
        return serviceRepository.findByNameContainingIgnoreCaseAndAvailableTrue(name);
    }

    public List<Service> getProviderServices(@NonNull String email) {
        User provider = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Provider not found"));
        return serviceRepository.findByProviderId(provider.getId());
    }

    public Service updateService(@NonNull Long id, @NonNull ServiceRequest req, @NonNull String email) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        if (!service.getProvider().getEmail().equals(email))
            throw new RuntimeException("Unauthorized");

        service.setName(req.getName());
        service.setDescription(req.getDescription());
        service.setCategory(req.getCategory());
        service.setPrice(req.getPrice());
        service.setLocation(req.getLocation());
        return serviceRepository.save(service);
    }

    public void deleteService(@NonNull Long id, @NonNull String email) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        if (!service.getProvider().getEmail().equals(email))
            throw new RuntimeException("Unauthorized");
        serviceRepository.delete(service);
    }
}
