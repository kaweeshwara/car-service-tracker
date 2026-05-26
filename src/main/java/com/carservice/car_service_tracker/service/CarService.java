package com.carservice.car_service_tracker.service;

import com.carservice.car_service_tracker.model.Car;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CarService {
    private final Map<Long, Car> store = new LinkedHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public CarService() {
        save(new Car(null, "Toyota", "Camry", 2020, "ABC-1234", "Alice Smith"));
        save(new Car(null, "Honda", "Civic", 2019, "XYZ-5678", "Bob Jones"));
    }

    public List<Car> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Car> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Car save(Car car) {
        if (car.getId() == null) {
            car.setId(idGen.getAndIncrement());
        }
        store.put(car.getId(), car);
        return car;
    }

    public void delete(Long id) {
        store.remove(id);
    }
}
