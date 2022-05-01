package com.parkingLotApp.models;

import com.parkingLotApp.exceptions.InvalidParkingTicketException;
import com.parkingLotApp.exceptions.NoParkingSpotAvailableException;
import com.parkingLotApp.models.vehicle.Vehicle;
import com.parkingLotApp.models.vehicle.VehicleType;
import com.parkingLotApp.utils.TSComparator;

import java.util.*;



public class ParkingFloor {

    String parkingFloorNumber;
    Map<String, ParkingSpot> occupiedSpots;
    Map<VehicleType, TreeSet<ParkingSpot>> vacantSpots;


    public ParkingFloor(String parkingFloorNumber, int slots){
        this.parkingFloorNumber = parkingFloorNumber;
        this.vacantSpots = new HashMap<VehicleType, TreeSet<ParkingSpot>>();
        this.occupiedSpots = new HashMap<String, ParkingSpot>();

        for(VehicleType type: VehicleType.values()){
            this.vacantSpots.put(type, new TreeSet<ParkingSpot>(
                    new TSComparator()
            ));
        }

        for(int i=0; i<slots; ++i){
            if(i==0){
                ParkingSpot parkingSpot=
                        new ParkingSpot(parkingFloorNumber+"_"+Integer.toString(i+1), VehicleType.TRUCK);
                this.vacantSpots.get(VehicleType.TRUCK).add(parkingSpot);
            }else if(i < 3){
                ParkingSpot parkingSpot=
                        new ParkingSpot(parkingFloorNumber+"_"+Integer.toString(i+1), VehicleType.BIKE);
                this.vacantSpots.get(VehicleType.BIKE).add(parkingSpot);
            }else{
                ParkingSpot parkingSpot=
                        new ParkingSpot(parkingFloorNumber+"_"+Integer.toString(i+1), VehicleType.CAR);
                this.vacantSpots.get(VehicleType.CAR).add(parkingSpot);
            }
        }
    }

    public TreeSet<ParkingSpot> getFreeSlotsForVehicleType(VehicleType vehicleType){
        return this.vacantSpots.get(vehicleType);
    }

    public TreeSet<ParkingSpot> getOccupiedSlotsForVehicleType(VehicleType vehicleType){
        TreeSet<ParkingSpot> parkingSpots = new TreeSet<ParkingSpot>(new TSComparator());
        for (Map.Entry mapElement : this.occupiedSpots.entrySet()) {
            ParkingSpot parkingSpot = ((ParkingSpot)mapElement.getValue());
            if(parkingSpot.getVehicleType() == vehicleType){
                parkingSpots.add(parkingSpot);
            }
        }
        return parkingSpots;
    }

    public int getFreeSlotsNumberForVehicleType(VehicleType vehicleType){
        return this.vacantSpots.get(vehicleType).size();
    }

    public String parkVehicle(Vehicle vehicle) throws NoParkingSpotAvailableException {
        if(this.vacantSpots.get(vehicle.getType()).size() == 0){
            throw new NoParkingSpotAvailableException("");
        }
        ParkingSpot spotToBeUsed = this.vacantSpots.get(vehicle.getType()).first();
        this.vacantSpots.get(vehicle.getType()).remove(spotToBeUsed);
        spotToBeUsed.setVehicle(vehicle);
        this.occupiedSpots.put(spotToBeUsed.getSlotNumber(), spotToBeUsed);
        return spotToBeUsed.getSlotNumber();
    }

    public Vehicle unparkVehicle(String ticket) throws InvalidParkingTicketException {
        if(!this.occupiedSpots.containsKey(ticket)){
            throw new InvalidParkingTicketException("Invalid Ticket");
        }

        ParkingSpot spot = this.occupiedSpots.get(ticket);
        Vehicle unparkedVehicle = spot.getVehicle();

        spot.setVehicle(null);
        this.vacantSpots.get(unparkedVehicle.getType()).add(spot);

        return unparkedVehicle;
    }
}
