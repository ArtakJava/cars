package org.example.service;

import org.example.dto.CarDto;

import java.util.List;

public interface CarService {

    CarDto createCar(CarDto carDto);

    List<CarDto> getCars();

    CarDto getCar(long carId);
}