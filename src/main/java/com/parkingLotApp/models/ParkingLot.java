package com.parkingLotApp.models;

import com.parkingLotApp.exceptions.InvalidParkingTicketException;
import com.parkingLotApp.exceptions.NoParkingSpotAvailableException;
import com.parkingLotApp.models.vehicle.Vehicle;
import com.parkingLotApp.models.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class ParkingLot {

    String parkingLotId;
    int floors;
    int slotsPerFloor;
    ArrayList<ParkingFloor> parkingFloors;

    public ParkingLot(String parkingLotId, int floors, int slotsPerFloor) {
        this.parkingLotId = parkingLotId;
        this.slotsPerFloor = slotsPerFloor;
        this.floors = floors;
        this.parkingFloors = new ArrayList<ParkingFloor>();
        for(int i=0; i<floors; ++i){
            this.parkingFloors.add(new ParkingFloor(parkingLotId+"_"+Integer.toString(i+1), slotsPerFloor));
        }
        // Created parking lot with 2 floors and 6 slots per floor
        System.out.println("Created parking lot with "+ floors+ " floors and "+ slotsPerFloor + " slots per floor");
    }

    public void parkVehicle(Vehicle vehicle) throws NoParkingSpotAvailableException {

        for(int floor=0; floor<this.floors; ++floor){
            String ticket = "";
            try{
                ticket = this.parkingFloors.get(floor).parkVehicle(vehicle);
                System.out.println("Parked vehicle. Ticket ID: "+ ticket);
            }catch (NoParkingSpotAvailableException e){

            }
        }
        throw  new NoParkingSpotAvailableException("No Parking Spot Found");
    }

    private int getFloorFromTicket(String ticket){
        String[] ticketParts = ticket.split("_",3);
        return Integer.parseInt(ticketParts[1]);
    }

    private int getSpotNumberFromTicket(String ticket) {
        String[] ticketParts = ticket.split("_",3);
        return Integer.parseInt(ticketParts[2]);
    }

    public void unparkVehicle(String ticket) throws InvalidParkingTicketException {

        int floor = this.getFloorFromTicket(ticket);
        if(floor<=0 || floor>=this.floors){
            throw new InvalidParkingTicketException("");
        }

        Vehicle unparkedVehicle = this.parkingFloors.get(floor-1).unparkVehicle(ticket);
        System.out.println("Unparked vehicle with Registration Number: " +
                    unparkedVehicle.getRegistration() +
                    "and Color: " +
                    unparkedVehicle.getColor());
    }

    public void getFreeSlotsNumberForVehicleType(VehicleType vehicleType){
        for(int floor=0; floor<this.floors; ++floor) {
            int freeSlots =  this.parkingFloors.get(floor).getFreeSlotsNumberForVehicleType(vehicleType);
            System.out.println("No. of free slots for " + vehicleType + " on Floor " + (floor+1) + " : " + freeSlots );
        }
    }

    public void getFreeSlotsForVehicleType(VehicleType vehicleType){
        for(int floor=0; floor<this.floors; ++floor) {
            TreeSet<ParkingSpot> freeSpots =  this.parkingFloors.get(floor).getFreeSlotsForVehicleType(vehicleType);
            ArrayList<Integer> slotList = new ArrayList<Integer>();
            for(ParkingSpot freeSpot : freeSpots){
                int spot = this.getSpotNumberFromTicket(freeSpot.getSlotNumber());
                slotList.add(spot);
            }
            System.out.println("Free slots for " + vehicleType + " on Floor " + (floor+1) + " : " +  slotList.toString());
        }
    }


    public void getOccupiedSlotsForVehicleType(VehicleType vehicleType){
        for(int floor=0; floor<this.floors; ++floor) {
            TreeSet<ParkingSpot> occupiedSpots =  this.parkingFloors.get(floor).getOccupiedSlotsForVehicleType(vehicleType);
            ArrayList<Integer> slotList = new ArrayList<Integer>();
            for(ParkingSpot occupiedSpot : occupiedSpots){
                int spot = this.getSpotNumberFromTicket(occupiedSpot.getSlotNumber());
                slotList.add(spot);
            }
            System.out.println("Occupied slots for " + vehicleType + " on Floor " + (floor+1) + " : " +  slotList.toString());
        }
    }
}
