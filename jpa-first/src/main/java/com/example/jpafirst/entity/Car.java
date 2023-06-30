package com.example.jpafirst.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "car")
//@Table(name = "car_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maker;
    private String model;
    private int yearmade;
}
