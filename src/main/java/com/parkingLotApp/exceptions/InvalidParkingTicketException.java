package com.parkingLotApp.exceptions;

public class InvalidParkingTicketException extends  Exception{
    public InvalidParkingTicketException(String message) {
        super(message);
    }
}
