package com.carservice.car_service_tracker.model;

public class Car {
    private Long id;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private String ownerName;

    public Car() {}

    public Car(Long id, String make, String model, int year, String licensePlate, String ownerName) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.ownerName = ownerName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getDisplayName() {
        return year + " " + make + " " + model;
    }
}
