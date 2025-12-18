package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.Vehicle;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Vehicle addVehicle(Long userId, Vehicle vehicle) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Validation required: capacityKg > 0, message must contain "Capacity"
        if (vehicle.getCapacityKg() == null || vehicle.getCapacityKg() <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        if (vehicle.getVehicleNumber() == null || vehicle.getVehicleNumber().isBlank()) {
            throw new IllegalArgumentException("Vehicle number is required");
        }
        if (vehicleRepository.existsByVehicleNumber(vehicle.getVehicleNumber())) {
            throw new IllegalArgumentException("Vehicle number already exists");
        }

        vehicle.setUser(user);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getByUserId(Long userId) {
        // optional: ensure user exists
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return vehicleRepository.findByUserId(userId);
    }

    @Override
    public Vehicle update(Long id, Vehicle vehicle) {
        Vehicle existing = getById(id);

        if (vehicle.getVehicleNumber() != null && !vehicle.getVehicleNumber().isBlank()) {
            existing.setVehicleNumber(vehicle.getVehicleNumber());
        }

        if (vehicle.getCapacityKg() != null) {
            if (vehicle.getCapacityKg() <= 0) {
                throw new IllegalArgumentException("Capacity must be positive");
            }
            existing.setCapacityKg(vehicle.getCapacityKg());
        }

        if (vehicle.getFuelEfficiency() != null) {
            existing.setFuelEfficiency(vehicle.getFuelEfficiency());
        }

        return vehicleRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Vehicle existing = getById(id);
        vehicleRepository.delete(existing);
    }
}