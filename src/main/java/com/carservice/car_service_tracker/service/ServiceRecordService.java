package com.carservice.car_service_tracker.service;

import com.carservice.car_service_tracker.model.Review;
import com.carservice.car_service_tracker.model.ServiceRecord;
import com.carservice.car_service_tracker.repository.ServiceRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceRecordService {
    private final ServiceRecordRepository repo;

    public ServiceRecordService(ServiceRecordRepository repo) {
        this.repo = repo;
    }

    public List<ServiceRecord> findAll() { return repo.findAll(); }
    public List<ServiceRecord> findByCarId(Long carId) { return repo.findByCarIdOrderByServiceDateDesc(carId); }
    public Optional<ServiceRecord> findById(Long id) { return repo.findById(id); }
    public ServiceRecord save(ServiceRecord record) { return repo.save(record); }

    public void addReview(Long recordId, Review review) {
        repo.findById(recordId).ifPresent(record -> {
            review.setReviewedAt(LocalDateTime.now());
            record.setReview(review);
            repo.save(record);
        });
    }

    public void delete(Long id) { repo.deleteById(id); }
    public void deleteByCarId(Long carId) { repo.deleteByCarId(carId); }
}
