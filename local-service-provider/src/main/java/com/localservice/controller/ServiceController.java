package com.localservice.controller;

import com.localservice.dto.DTOs.ServiceRequest;
import com.localservice.model.Service;
import com.localservice.service.ServiceProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ServiceController {

    private final ServiceProviderService serviceProviderService;

    public ServiceController(ServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }

    @GetMapping("/services")
    public ResponseEntity<List<Service>> getAll() {
        return ResponseEntity.ok(serviceProviderService.getAllAvailable());
    }

    @GetMapping("/services/category/{category}")
    public ResponseEntity<List<Service>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(serviceProviderService.getByCategory(category));
    }

    @GetMapping("/services/search")
    public ResponseEntity<List<Service>> search(@RequestParam String name) {
        return ResponseEntity.ok(serviceProviderService.search(name));
    }

    @GetMapping("/provider/services")
    public ResponseEntity<List<Service>> getMyServices(Principal principal) {
        return ResponseEntity.ok(serviceProviderService.getProviderServices(principal.getName()));
    }

    @PostMapping("/provider/services")
    public ResponseEntity<Service> create(@RequestBody ServiceRequest req, Principal principal) {
        return ResponseEntity.ok(serviceProviderService.createService(req, principal.getName()));
    }

    @PutMapping("/provider/services/{id}")
    public ResponseEntity<Service> update(@PathVariable Long id, @RequestBody ServiceRequest req, Principal principal) {
        return ResponseEntity.ok(serviceProviderService.updateService(id, req, principal.getName()));
    }

    @DeleteMapping("/provider/services/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Principal principal) {
        serviceProviderService.deleteService(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}
