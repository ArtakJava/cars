package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.car.CarNotFoundException;
import org.example.exception.garage.GarageIsFullException;
import org.example.exception.garage.GarageNotFoundException;
import org.example.exception.garage.GarageNotFoundWhenCreateCarException;
import org.example.messageManager.ErrorMessageManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleCarNotFoundException(final CarNotFoundException e) {
        log.info("404 {}", e.getMessage());
        return new ApiError(
                HttpStatus.NOT_FOUND,
                ErrorMessageManager.DATA_NOT_FOUND,
                e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleGarageNotFoundException(final GarageNotFoundException e) {
        log.info("404 {}", e.getMessage());
        return new ApiError(
                HttpStatus.NOT_FOUND,
                ErrorMessageManager.DATA_NOT_FOUND,
                e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleGarageNotFoundWhenCreateCarException(final GarageNotFoundWhenCreateCarException e) {
        log.info("400 {}", e.getMessage());
        return new ApiError(
                HttpStatus.BAD_REQUEST,
                ErrorMessageManager.GARAGE_NOT_FOUND_WHEN_CREATE_CAR,
                e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleGarageNotFoundWhenCreateCarException(final GarageIsFullException e) {
        log.info("400 {}", e.getMessage());
        return new ApiError(
                HttpStatus.BAD_REQUEST,
                ErrorMessageManager.GARAGE_IS_FULL_REASON,
                e.getMessage()
        );
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleThrowable(final Throwable e) {
        log.info("500 {}", e.getMessage());
        return new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorMessageManager.INTERNAL_SERVER_ERROR,
                e.getMessage()
        );
    }
}