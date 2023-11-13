package org.example.exception.garage;

public class GarageIsFullException extends RuntimeException  {

    public GarageIsFullException(String message) {
        super(message);
    }
}