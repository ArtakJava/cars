package org.example.service.garage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.GarageRepository;
import org.example.dto.garage.GarageDto;
import org.example.dto.garage.GarageFullDto;
import org.example.dto.garage.GarageShortDto;
import org.example.messageManager.InfoMessageManager;
import org.example.model.Car;
import org.example.model.Garage;
import org.example.dao.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GarageServiceImpl implements GarageService {
    private final GarageRepository garageRepository;
    private final CarRepository carRepository;

    @Override
    public GarageShortDto createGarage(GarageDto garageDto) {
        Garage garage = GarageMapper.mapToGarageEntity(garageDto);
        GarageShortDto result = GarageMapper.mapToGarageShortDto(garageRepository.save(garage));
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
        Garage garage = garageRepository.getReferenceById(garageId);
        List<Car> cars = carRepository.findByGarageId(garageId);
        log.info(String.format(InfoMessageManager.SUCCESS_GET_GARAGE, garageId));
        return GarageMapper.mapToGarageFullDto(garage, cars);
    }
}