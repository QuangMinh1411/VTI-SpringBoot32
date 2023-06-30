package com.example.jpafirst.service;

import com.example.jpafirst.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> getAll();
    Car getCarById(Long id);

    Car saveCar(Car car);

    Car updateCar(Long id, Car car);

    void deleteCar(Long id);
}
