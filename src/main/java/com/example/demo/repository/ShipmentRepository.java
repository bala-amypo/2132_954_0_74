package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findByVehicleId(Long vehicleId);
}