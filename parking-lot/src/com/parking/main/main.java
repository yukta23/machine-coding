package com.parking.main;

import com.parking.services.ParkingService;
import com.parking.services.ParkingServiceImpl;
import com.parking.utils.Color;
import com.parking.utils.Display;
import com.parking.utils.Type;

public class main {

	public static void main(String[] args) {
		ParkingService parking = new ParkingServiceImpl();
		parking.createParkingLot(123, 5, 4);
		parking.showParkingLotStatus(10);
		parking.showParkingLotStatus(123);
		parking.parkVehicle(Type.BIKE, "123895", Color.BLACK);
		parking.parkVehicle(Type.CAR, "123896", Color.BLUE);
		parking.parkVehicle(Type.BIKE, "123897", Color.CHOCOLATE_BROWN);
		parking.parkVehicle(Type.CAR, "123898", Color.RED);
		String ticket = parking.parkVehicle(Type.TRUCK, "123899", Color.BLACK);
		parking.parkVehicle(Type.TRUCK, "123840", Color.BLACK);
		parking.parkVehicle(Type.BIKE, "123841", Color.SILVER);
		parking.parkVehicle(Type.CAR, "123842", Color.WHITE);
		parking.parkVehicle(Type.TRUCK, "123843", Color.RED);
		parking.showParkingLotStatus(123);
		
		parking.unparkVehicle(ticket);
		parking.showParkingLotStatus(123);
		parking.displayFreeCountOrSlots(123, Type.TRUCK, Display.FREE_COUNT);
		parking.displayFreeCountOrSlots(123, Type.TRUCK, Display.FREE_SLOTS);
		parking.displayFreeCountOrSlots(123, Type.TRUCK, Display.OCCUPIED_SLOTS);
	}

}
