package com.carservice.car_service_tracker.service;

import com.carservice.car_service_tracker.model.Review;
import com.carservice.car_service_tracker.model.ServiceRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ServiceRecordService {
    private final Map<Long, ServiceRecord> store = new LinkedHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public ServiceRecordService() {
        ServiceRecord r1 = new ServiceRecord();
        r1.setCarId(1L);
        r1.setServiceType("Oil Change");
        r1.setDescription("Full synthetic oil change + filter replacement");
        r1.setServiceDate(LocalDate.of(2024, 3, 15));
        r1.setMileage(42000);
        r1.setCost(89.99);
        r1.setReview(new Review(5, "Quick and professional service. Very satisfied!"));
        save(r1);

        ServiceRecord r2 = new ServiceRecord();
        r2.setCarId(1L);
        r2.setServiceType("Brake Inspection");
        r2.setDescription("Front and rear brake pad check, rotors measured");
        r2.setServiceDate(LocalDate.of(2024, 7, 20));
        r2.setMileage(47500);
        r2.setCost(45.00);
        save(r2);

        ServiceRecord r3 = new ServiceRecord();
        r3.setCarId(2L);
        r3.setServiceType("Tire Rotation");
        r3.setDescription("All four tires rotated and balanced");
        r3.setServiceDate(LocalDate.of(2024, 5, 10));
        r3.setMileage(31000);
        r3.setCost(35.00);
        r3.setReview(new Review(4, "Good service, waited a bit longer than expected."));
        save(r3);
    }

    public List<ServiceRecord> findAll() {
        return new ArrayList<>(store.values());
    }

    public List<ServiceRecord> findByCarId(Long carId) {
        return store.values().stream()
                .filter(r -> carId.equals(r.getCarId()))
                .sorted(Comparator.comparing(ServiceRecord::getServiceDate).reversed())
                .collect(Collectors.toList());
    }

    public Optional<ServiceRecord> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public ServiceRecord save(ServiceRecord record) {
        if (record.getId() == null) {
            record.setId(idGen.getAndIncrement());
        }
        store.put(record.getId(), record);
        return record;
    }

    public void addReview(Long recordId, Review review) {
        ServiceRecord record = store.get(recordId);
        if (record != null) {
            review.setReviewedAt(java.time.LocalDateTime.now());
            record.setReview(review);
        }
    }

    public void delete(Long id) {
        store.remove(id);
    }
}
