package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.messageManager.ErrorMessageManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(final MissingServletRequestParameterException e) {
        log.info("400 {}", e.getMessage());
        return new ApiError(
                HttpStatus.BAD_REQUEST,
                ErrorMessageManager.INCORRECTLY_MADE_REQUEST,
                e.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.info("400 {}", e.getMessage());
        return new ApiError(
                HttpStatus.BAD_REQUEST,
                ErrorMessageManager.INCORRECTLY_MADE_REQUEST,
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