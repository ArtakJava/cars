package org.example.service.car;

import org.example.dto.CarDto;
import org.example.model.Car;

public class CarMapper {

    public static Car mapToCarEntity(CarDto carDto) {
        return Car.builder()
                .driverName(carDto.getDriverName())
                .brand(carDto.getBrand())
                .build();
    }

    public static CarDto mapToCarDto(Car car) {
        return CarDto.builder()
                .driverName(car.getDriverName())
                .brand(car.getBrand())
                .garageId(car.getGarage().getId())
                .build();
    }
}
