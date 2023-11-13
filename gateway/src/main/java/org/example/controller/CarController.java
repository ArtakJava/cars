package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.CarClient;
import org.example.dto.car.CarDto;
import org.example.messageManager.InfoMessageManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/car")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CarController {
    private final CarClient client;

    @PostMapping
    public ResponseEntity<Object> createCar(@Valid @RequestBody CarDto carDto) {
        log.info(String.format(InfoMessageManager.GET_CREATE_REQUEST, carDto));
        return client.createCar(carDto);
    }

    @GetMapping
    public ResponseEntity<Object> getCars() {
        log.info(InfoMessageManager.GET_ALL_CARS_REQUEST);
        return client.getCars();
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Object> getCar(@PathVariable long carId) {
        log.info(String.format(InfoMessageManager.GET_CAR_REQUEST, carId));
        return client.getCar(carId);
    }
}