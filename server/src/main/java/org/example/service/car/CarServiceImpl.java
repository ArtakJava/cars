package org.example.service.car;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.CarRepository;
import org.example.dao.GarageRepository;
import org.example.dto.CarDto;
import org.example.exception.GarageIsFullException;
import org.example.exception.GarageNotFoundException;
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
    @Value("${garage.capacity:4}")
    private long garageCapacity;

    @Override
    public CarDto createCar(CarDto carDto) {
        Optional<Garage> garage = garageRepository.findById(carDto.getGarageId());
        if (garage.isPresent()) {
            long garageId = garage.get().getId();
            Long carsCountInGarage = carRepository.findCarsCountInGarage(garageId);
            if (carsCountInGarage < garageCapacity) {
                Car car = CarMapper.mapToCarEntity(carDto);
                CarDto result = CarMapper.mapToCarDto(carRepository.save(car));
                log.info(String.format(InfoMessageManager.SUCCESS_CREATE, result));
                return result;
            } else {
                throw new GarageIsFullException(String.format(ErrorMessageManager.GARAGE_IS_FULL, garageId));
            }
        } else {
            throw new GarageNotFoundException(String.format(ErrorMessageManager.GARAGE_NOT_FOUND, carDto.getGarageId()));
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
        Car car = carRepository.getReferenceById(carId);
        log.info(String.format(InfoMessageManager.SUCCESS_GET_CAR, carId));
        return CarMapper.mapToCarDto(car);
    }
}