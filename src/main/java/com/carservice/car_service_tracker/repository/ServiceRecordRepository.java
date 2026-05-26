package com.carservice.car_service_tracker.repository;

import com.carservice.car_service_tracker.model.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
    List<ServiceRecord> findByCarIdOrderByServiceDateDesc(Long carId);
    void deleteByCarId(Long carId);
}
