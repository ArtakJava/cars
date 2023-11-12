package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.GarageClient;
import org.example.dto.garage.GarageDto;
import org.example.messageManager.InfoMessageManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/garage")
@RequiredArgsConstructor
@Slf4j
@Validated
public class GarageController {
    private final GarageClient client;

    @PostMapping
    public ResponseEntity<Object> createGarage(@Valid @RequestBody GarageDto garageDto) {
        log.info(String.format(InfoMessageManager.GET_CREATE_REQUEST, garageDto));
        return client.createGarage(garageDto);
    }

    @GetMapping
    public ResponseEntity<Object> getGarages() {
        log.info(InfoMessageManager.GET_ALL_GARAGES_REQUEST);
        return client.getGarages();
    }

    @GetMapping("/{garageId}")
    public ResponseEntity<Object> getGarage(@PathVariable long garageId) {
        log.info(String.format(InfoMessageManager.GET_GARAGE_REQUEST, garageId));
        return client.getGarage(garageId);
    }
}