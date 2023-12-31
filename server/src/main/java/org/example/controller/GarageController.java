package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.garage.GarageDto;
import org.example.dto.garage.GarageFullDto;
import org.example.dto.garage.GarageShortDto;
import org.example.messageManager.InfoMessageManager;
import org.example.service.garage.GarageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/garage")
@RequiredArgsConstructor
@Slf4j
public class GarageController {
    private final GarageService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GarageDto createGarage(@RequestBody GarageDto garageDto) {
        log.info(String.format(InfoMessageManager.GET_CREATE_REQUEST, garageDto));
        return service.createGarage(garageDto);
    }

    @GetMapping
    public List<GarageShortDto> getGarages() {
        log.info(InfoMessageManager.GET_ALL_GARAGES_REQUEST);
        return service.getGarages();
    }

    @GetMapping("/{garageId}")
    public GarageFullDto getGarage(@PathVariable long garageId) {
        log.info(String.format(InfoMessageManager.GET_GARAGE_REQUEST, garageId));
        return service.getGarage(garageId);
    }
}