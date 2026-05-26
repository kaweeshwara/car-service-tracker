package com.carservice.car_service_tracker.controller;

import com.carservice.car_service_tracker.model.Car;
import com.carservice.car_service_tracker.model.Review;
import com.carservice.car_service_tracker.model.ServiceRecord;
import com.carservice.car_service_tracker.service.CarService;
import com.carservice.car_service_tracker.service.ServiceRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/records")
public class ServiceRecordController {
    private final ServiceRecordService recordService;
    private final CarService carService;

    public ServiceRecordController(ServiceRecordService recordService, CarService carService) {
        this.recordService = recordService;
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ServiceRecord record = recordService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record not found: " + id));
        Car car = carService.findById(record.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));
        model.addAttribute("record", record);
        model.addAttribute("car", car);
        model.addAttribute("review", record.hasReview() ? record.getReview() : new Review());
        return "records/detail";
    }

    @GetMapping("/new")
    public String newForm(@RequestParam Long carId, Model model) {
        Car car = carService.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found: " + carId));
        ServiceRecord record = new ServiceRecord();
        record.setCarId(carId);
        record.setServiceDate(LocalDate.now());
        model.addAttribute("record", record);
        model.addAttribute("car", car);
        model.addAttribute("serviceTypes", new String[]{
                "Oil Change", "Tire Rotation", "Brake Inspection", "Air Filter",
                "Spark Plugs", "Battery Replacement", "Transmission Service",
                "Coolant Flush", "Alignment", "Other"
        });
        return "records/form";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam Long carId,
            @RequestParam String serviceType,
            @RequestParam String description,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate serviceDate,
            @RequestParam int mileage,
            @RequestParam double cost,
            @RequestParam(required = false) Long recordId) {

        ServiceRecord record = recordId != null
                ? recordService.findById(recordId).orElse(new ServiceRecord())
                : new ServiceRecord();

        record.setCarId(carId);
        record.setServiceType(serviceType);
        record.setDescription(description);
        record.setServiceDate(serviceDate);
        record.setMileage(mileage);
        record.setCost(cost);
        recordService.save(record);
        return "redirect:/cars/" + carId;
    }

    @GetMapping("/{id}/review")
    public String reviewForm(@PathVariable Long id, Model model) {
        ServiceRecord record = recordService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record not found: " + id));
        Car car = carService.findById(record.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));
        model.addAttribute("record", record);
        model.addAttribute("car", car);
        Review existing = record.hasReview() ? record.getReview() : new Review();
        model.addAttribute("review", existing);
        return "records/review-form";
    }

    @PostMapping("/{id}/review")
    public String saveReview(@PathVariable Long id,
                             @RequestParam int rating,
                             @RequestParam String comment) {
        Review review = new Review(rating, comment);
        recordService.addReview(id, review);
        return "redirect:/records/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        ServiceRecord record = recordService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record not found: " + id));
        Long carId = record.getCarId();
        recordService.delete(id);
        return "redirect:/cars/" + carId;
    }
}
