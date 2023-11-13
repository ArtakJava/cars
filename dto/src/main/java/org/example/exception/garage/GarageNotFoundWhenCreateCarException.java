package org.example.exception.garage;

public class GarageNotFoundWhenCreateCarException extends RuntimeException  {

    public GarageNotFoundWhenCreateCarException(String message) {
        super(message);
    }
}