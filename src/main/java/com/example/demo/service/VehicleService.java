package com.example.demo.service;

import com.example.demo.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle addVehicle(Long userId, Vehicle vehicle);
    Vehicle getById(Long id);
    List<Vehicle> getAll();
    List<Vehicle> getByUserId(Long userId);
    Vehicle update(Long id, Vehicle vehicle);
    void delete(Long id);
}