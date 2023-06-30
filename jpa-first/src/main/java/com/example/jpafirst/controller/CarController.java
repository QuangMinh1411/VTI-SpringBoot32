package com.example.jpafirst.controller;

import com.example.jpafirst.entity.Car;
import com.example.jpafirst.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService service;

    @PostMapping
    public ResponseEntity<?> saveCar(@RequestBody Car car){
        return new ResponseEntity<>(service.saveCar(car), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(service.getCarById(id),HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody Car car){
        return new ResponseEntity<>(service.updateCar(id,car),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id){
        service.deleteCar(id);
        return ResponseEntity.accepted().body("Delete success");
    }
}
