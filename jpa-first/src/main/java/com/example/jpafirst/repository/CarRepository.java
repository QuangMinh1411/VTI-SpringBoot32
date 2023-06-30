package com.example.jpafirst.repository;

import com.example.jpafirst.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
}
