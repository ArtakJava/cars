package org.example.service.car;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.CarRepository;
import org.example.dao.GarageRepository;
import org.example.dto.car.CarDto;
import org.example.exception.car.CarNotFoundException;
import org.example.exception.garage.GarageIsFullException;
import org.example.exception.garage.GarageNotFoundWhenCreateCarException;
import org.example.messageManager.ErrorMessageManager;
import org.example.messageManager.InfoMessageManager;
import org.example.model.Car;
import org.example.model.Garage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final GarageRepository garageRepository;

    @Value("${application.garage.capacity}")
    public long garageCapacity;

    @Override
    public CarDto createCar(CarDto carDto) {
        Optional<Garage> garage = garageRepository.findById(carDto.getGarageId());
        if (garage.isPresent()) {
            long garageId = garage.get().getId();
            Long carsCountInGarage = carRepository.findCarsCountInGarage(garageId);
            if (carsCountInGarage < garageCapacity) {
                Car car = CarMapper.mapToCarEntity(carDto);
                car.setGarage(garage.get());
                CarDto result = CarMapper.mapToCarDto(carRepository.save(car));
                log.info(String.format(InfoMessageManager.SUCCESS_CREATE, result));
                return result;
            } else {
                throw new GarageIsFullException(String.format(ErrorMessageManager.GARAGE_IS_FULL, garageId));
            }
        } else {
            throw new GarageNotFoundWhenCreateCarException(String.format(ErrorMessageManager.GARAGE_NOT_FOUND, carDto.getGarageId()));
        }
    }

    @Override
    public List<CarDto> getCars() {
        List<CarDto> cars = carRepository.findAll().stream()
                .map(CarMapper::mapToCarDto)
                .collect(Collectors.toList());
        log.info(InfoMessageManager.SUCCESS_GET_ALL_CARS_REQUEST);
        return cars;
    }

    @Override
    public CarDto getCar(long carId) {
        Optional<Car> car = carRepository.findById(carId);
        if (car.isPresent()) {
            log.info(String.format(InfoMessageManager.SUCCESS_GET_CAR, carId));
            return CarMapper.mapToCarDto(car.get());
        } else {
            throw new CarNotFoundException(String.format(ErrorMessageManager.CAR_NOT_FOUND, carId));
        }
    }
}