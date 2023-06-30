package com.example.jpafirst.service;

import com.example.jpafirst.entity.Car;
import com.example.jpafirst.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepo;
    @Override
    public List<Car> getAll() {
        return carRepo.findAll();
    }

    @Override
    public Car getCarById(Long id) {

        return carRepo.findById(id).orElse(null);
    }

    @Override
    public Car saveCar(Car car) {

        return carRepo.save(car);
    }

    @Override
    public Car updateCar(Long id, Car car) {
        if(getCarById(id)!=null){
            Car exist = getCarById(id);
            exist.setModel(car.getModel());
            exist.setMaker(car.getMaker());
            exist.setYearmade(car.getYearmade());
            return carRepo.save(exist);

        }
        return null;
    }

    @Override
    public void deleteCar(Long id) {
        carRepo.deleteById(id);
    }
}
