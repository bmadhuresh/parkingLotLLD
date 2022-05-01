package com.parkingLotApp.utils;

import com.parkingLotApp.models.ParkingSpot;

import java.util.Comparator;

public class TSComparator implements Comparator<ParkingSpot> {
    @Override
    public int compare(ParkingSpot a, ParkingSpot b) {
        return a.getSlotNumber().compareTo(b.getSlotNumber());
    }
};