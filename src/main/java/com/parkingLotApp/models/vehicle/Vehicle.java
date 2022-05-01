package com.parkingLotApp.models.vehicle;


public class Vehicle {
    VehicleType vehicleType;
    String registration;
    String color;

    public Vehicle(VehicleType vehicleType, String registration, String color) {
        this.vehicleType = vehicleType;
        this.registration = registration;
        this.color = color;
    }

    public VehicleType getType() {
        return vehicleType;
    }

    public void setType(VehicleType type) {
        this.vehicleType = type;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
