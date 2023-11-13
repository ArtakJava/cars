package org.example.service.garage;

import org.example.dto.garage.GarageDto;
import org.example.dto.garage.GarageFullDto;
import org.example.dto.garage.GarageShortDto;

import java.util.List;

public interface GarageService {

    GarageDto createGarage(GarageDto garageDto);

    List<GarageShortDto> getGarages();

    GarageFullDto getGarage(long garageId);
}