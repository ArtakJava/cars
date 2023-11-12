package org.example.exception;

public class GarageIsFullException extends RuntimeException  {
    public GarageIsFullException(String message) {
        super(message);
    }
}