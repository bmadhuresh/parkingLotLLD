package com.parkingLotApp.exceptions;

public class NoParkingSpotAvailableException extends Exception {
    public NoParkingSpotAvailableException(String message) {
        super(message);
    }
}
