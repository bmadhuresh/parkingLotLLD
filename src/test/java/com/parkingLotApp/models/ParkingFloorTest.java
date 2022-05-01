package com.parkingLotApp.models;

import com.parkingLotApp.models.vehicle.Vehicle;
import com.parkingLotApp.models.vehicle.VehicleType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ParkingFloorTest {

    @Test
    void getFreeSlotsForVehicleType() {
        ParkingFloor pf = new ParkingFloor("PRN1234_1", 7);
        for(VehicleType type: VehicleType.values()) {
            TreeSet<ParkingSpot> parkingSpots = pf.getFreeSlotsForVehicleType(type);
            for(ParkingSpot spot : parkingSpots ){
                System.out.println(type+ " : "+ spot.getSlotNumber());
            }
        }

    }

    @Test
    void getFreeSlotsNumberForVehicleType() {
    }

    @Test
    void parkVehicle() {
        ParkingFloor pf = new ParkingFloor("PRN1234_1", 7);
        Vehicle vehicle = new Vehicle(VehicleType.CAR, "abcdefg", "blue");
        try{
            String ticket1 = pf.parkVehicle(vehicle);
            String ticket2 = pf.parkVehicle(vehicle);
            String ticket3 = pf.parkVehicle(vehicle);
            System.out.println(ticket1);
            System.out.println(ticket2);
            System.out.println(ticket3);

            pf.unparkVehicle(ticket1);

            for(VehicleType type: VehicleType.values()) {
                TreeSet<ParkingSpot> parkingSpots = pf.getFreeSlotsForVehicleType(type);
                for(ParkingSpot spot : parkingSpots ){
                    System.out.println(type+ " : "+ spot.getSlotNumber());
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }

    @Test
    void unparkVehicle() {
        ArrayList<Integer> A = new ArrayList<Integer>();
        A.add(1);
        A.add(2);
        A.add(3);

        System.out.println(A);
    }
}