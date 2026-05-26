package com.carservice.car_service_tracker;

import com.carservice.car_service_tracker.model.Car;
import com.carservice.car_service_tracker.model.Review;
import com.carservice.car_service_tracker.model.ServiceRecord;
import com.carservice.car_service_tracker.service.CarService;
import com.carservice.car_service_tracker.service.ServiceRecordService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements ApplicationRunner {
    private final CarService carService;
    private final ServiceRecordService recordService;

    public DataSeeder(CarService carService, ServiceRecordService recordService) {
        this.carService = carService;
        this.recordService = recordService;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (!carService.isEmpty()) return;

        Car camry = carService.save(new Car("Toyota", "Camry", 2020, "ABC-1234", "Alice Smith"));
        Car civic = carService.save(new Car("Honda", "Civic", 2019, "XYZ-5678", "Bob Jones"));

        ServiceRecord r1 = new ServiceRecord();
        r1.setCarId(camry.getId());
        r1.setServiceType("Oil Change");
        r1.setDescription("Full synthetic oil change + filter replacement");
        r1.setServiceDate(LocalDate.of(2024, 3, 15));
        r1.setMileage(42000);
        r1.setCost(89.99);
        r1.setReview(new Review(5, "Quick and professional service. Very satisfied!"));
        recordService.save(r1);

        ServiceRecord r2 = new ServiceRecord();
        r2.setCarId(camry.getId());
        r2.setServiceType("Brake Inspection");
        r2.setDescription("Front and rear brake pad check, rotors measured");
        r2.setServiceDate(LocalDate.of(2024, 7, 20));
        r2.setMileage(47500);
        r2.setCost(45.00);
        recordService.save(r2);

        ServiceRecord r3 = new ServiceRecord();
        r3.setCarId(civic.getId());
        r3.setServiceType("Tire Rotation");
        r3.setDescription("All four tires rotated and balanced");
        r3.setServiceDate(LocalDate.of(2024, 5, 10));
        r3.setMileage(31000);
        r3.setCost(35.00);
        r3.setReview(new Review(4, "Good service, waited a bit longer than expected."));
        recordService.save(r3);
    }
}
