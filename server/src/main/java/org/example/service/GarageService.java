package org.example.service;

import org.example.dto.garage.GarageFullDto;
import org.example.dto.garage.GarageShortDto;

import java.util.List;

public interface GarageService {

    GarageFullDto createGarage(GarageShortDto garageDto);

    List<GarageShortDto> getGarages();

    GarageFullDto getGarage(long garageId);
}