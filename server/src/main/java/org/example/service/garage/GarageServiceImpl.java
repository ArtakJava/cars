package org.example.service.garage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.CarRepository;
import org.example.dao.GarageRepository;
import org.example.dto.garage.GarageDto;
import org.example.dto.garage.GarageFullDto;
import org.example.dto.garage.GarageShortDto;
import org.example.exception.garage.GarageNotFoundException;
import org.example.messageManager.ErrorMessageManager;
import org.example.messageManager.InfoMessageManager;
import org.example.model.Car;
import org.example.model.Garage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GarageServiceImpl implements GarageService {
    private final GarageRepository garageRepository;
    private final CarRepository carRepository;

    @Override
    public GarageDto createGarage(GarageDto garageDto) {
        Garage garage = GarageMapper.mapToGarageEntity(garageDto);
        GarageDto result = GarageMapper.mapToGarageDto(garageRepository.save(garage));
        log.info(String.format(InfoMessageManager.SUCCESS_CREATE, result));
        return result;
    }

    @Override
    public List<GarageShortDto> getGarages() {
        List<GarageShortDto> garages = garageRepository.findAll().stream()
                .map(GarageMapper::mapToGarageShortDto)
                .collect(Collectors.toList());
        List<Car> allCars = carRepository.findAll();
        Map<Long, List<Car>> carsByGarageId = allCars.stream()
                        .collect(Collectors.groupingBy(car -> car.getGarage().getId()));
        log.info(InfoMessageManager.SUCCESS_GET_ALL_GARAGES_REQUEST);
        return garages.stream()
                .peek(garage -> garage.setCarsCount(carsByGarageId.getOrDefault(garage.getId(), new ArrayList<>()).size()))
                .collect(Collectors.toList());
    }

    @Override
    public GarageFullDto getGarage(long garageId) {
        Optional<Garage> garage = garageRepository.findById(garageId);
        if (garage.isPresent()) {
            List<Car> cars = carRepository.findByGarageId(garageId);
            log.info(String.format(InfoMessageManager.SUCCESS_GET_GARAGE, garageId));
            return GarageMapper.mapToGarageFullDto(garage.get(), cars);
        } else {
            throw new GarageNotFoundException(String.format(ErrorMessageManager.CAR_NOT_FOUND, garageId));
        }

    }
}