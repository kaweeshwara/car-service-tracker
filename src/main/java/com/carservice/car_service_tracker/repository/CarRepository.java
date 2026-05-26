package com.carservice.car_service_tracker.repository;

import com.carservice.car_service_tracker.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {}
