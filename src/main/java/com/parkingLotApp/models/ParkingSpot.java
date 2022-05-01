package com.parkingLotApp.models;

import com.parkingLotApp.models.vehicle.Vehicle;
import com.parkingLotApp.models.vehicle.VehicleType;

public class ParkingSpot {

    String slotNumber;
    VehicleType vehicleType;
    Vehicle vehicle;

    public ParkingSpot(String slotNumber, VehicleType vehicleType) {
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
        this.vehicle = null;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
