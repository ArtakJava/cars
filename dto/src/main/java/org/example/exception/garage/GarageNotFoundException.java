package org.example.exception.garage;

public class GarageNotFoundException extends RuntimeException  {

    public GarageNotFoundException(String message) {
        super(message);
    }
}