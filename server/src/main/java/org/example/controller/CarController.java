package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CarDto;
import org.example.messageManager.MessageManager;
import org.example.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/car")
@RequiredArgsConstructor
@Slf4j
public class CarController {
    private final CarService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto createCar(@RequestBody CarDto carDto) {
        log.info(String.format(MessageManager.GET_CREATE_REQUEST, carDto));
        return service.createCar(carDto);
    }

    @GetMapping
    public List<CarDto> getCars() {
        log.info(MessageManager.GET_ALL_CARS_REQUEST);
        return service.getCars();
    }

    @GetMapping("/{carId}")
    public CarDto getCar(@PathVariable long carId) {
        log.info(String.format(MessageManager.GET_CAR_REQUEST, carId));
        return service.getCar(carId);
    }
}