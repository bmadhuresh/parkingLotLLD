package com.parkingLotApp.controllers;

import com.parkingLotApp.exceptions.InvalidParkingTicketException;
import com.parkingLotApp.exceptions.NoParkingSpotAvailableException;
import com.parkingLotApp.models.ParkingLot;
import com.parkingLotApp.models.vehicle.Vehicle;
import com.parkingLotApp.models.vehicle.VehicleType;

public class ParkingLotManager {

    ParkingLot parkingLot;

    VehicleType getVehicleType (String vehicleType){
        switch (vehicleType){
            case "CAR": {return VehicleType.CAR;}
            case "TRUCK": {return VehicleType.TRUCK;}
            case "BIKE": {return VehicleType.BIKE;}
            default: {return null;}
        }
    }

    void create_parking_lot(String parkingLotId, int floors, int slotsPerFloor){
        this.parkingLot = new ParkingLot(parkingLotId, floors, slotsPerFloor);
    }

    void park_vehicle(VehicleType vehicleType, String regNo, String color){
        Vehicle vehicle = new Vehicle(vehicleType,regNo,color);
        try{
            this.parkingLot.parkVehicle(vehicle);
        }catch (NoParkingSpotAvailableException e){
            System.out.println("Parking Lot Full");
        }
    }

    void unpark_vehicle(String ticketId){
        try{
            this.parkingLot.unparkVehicle(ticketId);
        }catch (InvalidParkingTicketException e){
            System.out.println("Invalid Ticket");
        }
    }

    void display(String displayType, VehicleType vehicleType){
        switch (displayType){
            case "free_count": {
                this.parkingLot.getFreeSlotsNumberForVehicleType(vehicleType);
                break;
            }
            case "free_slots": {
                this.parkingLot.getFreeSlotsForVehicleType(vehicleType);
                break;
            }
            case  "occupied_slots": {
                this.parkingLot.getOccupiedSlotsForVehicleType(vehicleType);
                break;
            }
            default: {
                System.out.println("Display Command Error Received displayType " + displayType);
            }
        }
    }

    public void RunCommand(String command){
        String[] parsedCommand = command.split(" ");
        switch (parsedCommand[0]){
            case "create_parking_lot": {
                this.create_parking_lot(
                        parsedCommand[1],
                        Integer.parseInt(parsedCommand[2]),
                        Integer.parseInt(parsedCommand[3])
                );
                break;
            }
            case "park_vehicle": {
                VehicleType vehicleType = this.getVehicleType(parsedCommand[1]);
                String registrationNum = parsedCommand[2];
                String color = parsedCommand[3];
                this.park_vehicle(vehicleType, registrationNum, color);
                break;
            }
            case "unpark_vehicle": {
                String ticketNum = parsedCommand[1];
                this.unpark_vehicle(ticketNum);
                break;
            }
            case "display": {
                String displayType = parsedCommand[1];
                VehicleType vehicleType = this.getVehicleType(parsedCommand[2]);
                this.display(displayType, vehicleType);
                break;
            }
            default: {
                System.out.println("Command Error Received command " + parsedCommand[0]);
            }
        }
    }
}
