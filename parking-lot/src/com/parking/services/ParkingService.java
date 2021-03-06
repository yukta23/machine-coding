package com.parking.services;

import com.parking.models.ParkingLot;
import com.parking.utils.Color;
import com.parking.utils.Display;
import com.parking.utils.Type;

public interface ParkingService {
	ParkingLot createParkingLot(int parkingId, int floors, int slotsPerFloor);
	String parkVehicle(Type vehicleType, String regNo, Color color);
	void unparkVehicle(String ticketId);
	void showParkingLotStatus(int parkingId);
	void displayFreeCountOrSlots(int parkingId, Type vehicleType, Display displayType);
	
}
