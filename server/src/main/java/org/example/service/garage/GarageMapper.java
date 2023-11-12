package org.example.service.garage;

import org.example.dto.garage.GarageDto;
import org.example.dto.garage.GarageFullDto;
import org.example.dto.garage.GarageShortDto;
import org.example.model.Car;
import org.example.model.Garage;
import org.example.service.car.CarMapper;

import java.util.List;
import java.util.stream.Collectors;

public class GarageMapper {

    public static Garage mapToGarageEntity(GarageDto garageDto) {
        return Garage.builder()
                .name(garageDto.getName())
                .build();
    }

    public static GarageShortDto mapToGarageShortDto(Garage garage) {
        return GarageShortDto.builder()
                .id(garage.getId())
                .name(garage.getName())
                .build();
    }

    public static GarageFullDto mapToGarageFullDto(Garage garage, List<Car> cars) {
        return GarageFullDto.builder()
                .id(garage.getId())
                .name(garage.getName())
                .cars(cars.stream()
                        .map(CarMapper::mapToCarDto)
                        .collect(Collectors.toList()))
                .build();
    }
}