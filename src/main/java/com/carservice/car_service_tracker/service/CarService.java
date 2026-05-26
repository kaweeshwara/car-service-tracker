package com.carservice.car_service_tracker.service;

import com.carservice.car_service_tracker.model.Car;
import com.carservice.car_service_tracker.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository repo;

    public CarService(CarRepository repo) {
        this.repo = repo;
    }

    public List<Car> findAll() { return repo.findAll(); }
    public Optional<Car> findById(Long id) { return repo.findById(id); }
    public Car save(Car car) { return repo.save(car); }
    public void delete(Long id) { repo.deleteById(id); }
    public boolean isEmpty() { return repo.count() == 0; }
}
