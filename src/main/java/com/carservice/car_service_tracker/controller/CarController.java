package com.carservice.car_service_tracker.controller;

import com.carservice.car_service_tracker.model.Car;
import com.carservice.car_service_tracker.model.ServiceRecord;
import com.carservice.car_service_tracker.service.CarService;
import com.carservice.car_service_tracker.service.ServiceRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final ServiceRecordService serviceRecordService;

    public CarController(CarService carService, ServiceRecordService serviceRecordService) {
        this.carService = carService;
        this.serviceRecordService = serviceRecordService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "cars/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Car car = carService.findById(id).orElseThrow(() -> new IllegalArgumentException("Car not found: " + id));
        List<ServiceRecord> records = serviceRecordService.findByCarId(id);
        double totalCost = records.stream().mapToDouble(ServiceRecord::getCost).sum();
        long reviewCount = records.stream().filter(ServiceRecord::hasReview).count();
        model.addAttribute("car", car);
        model.addAttribute("records", records);
        model.addAttribute("totalCost", totalCost);
        model.addAttribute("reviewCount", reviewCount);
        return "cars/detail";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("car", new Car());
        return "cars/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Car car = carService.findById(id).orElseThrow(() -> new IllegalArgumentException("Car not found: " + id));
        model.addAttribute("car", car);
        return "cars/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Car car) {
        carService.save(car);
        return "redirect:/cars";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        carService.delete(id);
        return "redirect:/cars";
    }
}
