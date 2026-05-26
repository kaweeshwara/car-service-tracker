package com.carservice.car_service_tracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "service_record")
public class ServiceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long carId;
    private String serviceType;
    @Column(length = 1000)
    private String description;
    private LocalDate serviceDate;
    private int mileage;
    private double cost;

    @Embedded
    private Review review = new Review();

    public ServiceRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCarId() { return carId; }
    public void setCarId(Long carId) { this.carId = carId; }
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getServiceDate() { return serviceDate; }
    public void setServiceDate(LocalDate serviceDate) { this.serviceDate = serviceDate; }
    public int getMileage() { return mileage; }
    public void setMileage(int mileage) { this.mileage = mileage; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public Review getReview() { return review; }
    public void setReview(Review review) { this.review = review; }

    public boolean hasReview() {
        return review != null && review.getRating() > 0;
    }
}
