package com.example.demo.controller;

import com.example.demo.entity.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    private final VehicleService vehicleService; // constructor injection

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Add vehicle for a user
    @PostMapping("/vehicles/{userId}")
    public Vehicle addVehicle(@PathVariable Long userId, @RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(userId, vehicle);
    }

    @GetMapping("/vehicles/{id}")
    public Vehicle getById(@PathVariable Long id) {
        return vehicleService.getById(id);
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getAll() {
        return vehicleService.getAll();
    }

    @GetMapping("/vehicles/user/{userId}")
    public List<Vehicle> getByUser(@PathVariable Long userId) {
        return vehicleService.getByUserId(userId);
    }

    @PutMapping("/vehicles/{id}")
    public Vehicle update(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return vehicleService.update(id, vehicle);
    }

    @DeleteMapping("/vehicles/{id}")
    public String delete(@PathVariable Long id) {
        vehicleService.delete(id);
        return "Deleted";
    }
}