package com.parking.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.parking.models.ParkingLot;
import com.parking.models.Slot;
import com.parking.models.Vehicle;
import com.parking.utils.Color;
import com.parking.utils.Display;
import com.parking.utils.Type;

public class ParkingServiceImpl implements ParkingService {
	public HashMap<Integer, ParkingLot> parkingMap = new HashMap<>();
	public int parkingId;
	@Override
	public ParkingLot createParkingLot(int parkingId, int floors, int slotsPerFloor) {
		this.parkingId = parkingId;
		ParkingLot parkingLot = new ParkingLot(parkingId, floors, slotsPerFloor);
		ArrayList<Slot> slots = new ArrayList<Slot>();
		int k = 0;
		for( int i = 0 ; i < floors; i++ ) {
			for( int j = 0; j < slotsPerFloor; j++ ) {
				int typePick = new Random().nextInt(Type.values().length);
				Slot s = new Slot(++k, parkingId, i + 1, Type.values()[typePick]);
				slots.add(s);
			}
		}
		parkingLot.setParkingSlots(slots);
		this.parkingMap.put(parkingId, parkingLot);
		return parkingLot;
	}

	@Override
	public String parkVehicle(Type vehicleType, String regNo, Color color) {
		ParkingLot pl = this.parkingLotById(this.parkingId);
		if (pl == null )
			return "Parking lot dne";
		ArrayList<Slot> slots = pl.getParkingSlots();
		Slot slotBooked = null;
		Vehicle vehicle = new Vehicle(regNo, vehicleType, color);
		for( int i = 0; i < slots.size(); i++ ) {
			Slot s = slots.get(i);
			if (s.getType() == vehicleType && s.isVacant()) {
				s.setVehicle(vehicle);
				slotBooked = s;
				break;
			}
		}
		String ticket = "Parking Lot Full";
		if (slotBooked != null )
			ticket = parkingId + "_" + slotBooked.getFloorId() + "_" + slotBooked.getId(); 
		System.out.println(ticket);
		return ticket;
	}

	@Override
	public void showParkingLotStatus(int parkingId) {
		ParkingLot pl = this.parkingLotById(parkingId);
		if (pl != null)
			this.printParkingLot(pl);
		else 
			System.out.println("Given parking lot doesn't exist");
	}

	@Override
	public void unparkVehicle(String ticketId) {
		//parkingId_floorId_slotId
		String[] tokens = ticketId.split("_");
		int parkingId = Integer.parseInt(tokens[0]);
		int floorId = Integer.parseInt(tokens[1]);
		int slotId = Integer.parseInt(tokens[2]);
		ParkingLot pl = this.parkingLotById(parkingId);
		if (pl != null) {
			pl.getParkingSlots().get(slotId-1).setVehicle(null);
		}
	}
	//Private handlers
	private ParkingLot parkingLotById(int parkingId) {
		if (this.parkingMap.containsKey(parkingId)) {
			return this.parkingMap.get(parkingId);
		}
		return null;
	}
	
	private void printParkingLot(ParkingLot  parkingLot) {
		System.out.println("*********Printing Parking Lot Status************");
		ArrayList<Slot> slots = parkingLot.getParkingSlots();
		for( int i = 0; i < slots.size(); i++ ) {
			Slot s = slots.get(i);
			System.out.println("Slot: "+ s.getId() +
					", Floor: " +  s.getFloorId() + 
					", Type: " + s.getType() +
					", Vacancy: " + s.isVacant());
		}
		
	}

	@Override
	public void displayFreeCountOrSlots(int parkingId, Type vehicleType, Display dtype) {
		ParkingLot pl = this.parkingLotById(parkingId);
		ArrayList<Slot> slots = pl.getParkingSlots();
		ArrayList<Slot> vacantSlotsBasedOnType = new ArrayList<>();
		ArrayList<Slot> slotsBasedOnType = new ArrayList<>();
		slots.forEach(slot -> {
			if (slot.getType() == vehicleType) {
				slotsBasedOnType.add(slot);
				if (slot.isVacant()) {
					vacantSlotsBasedOnType.add(slot);
					if (dtype == Display.FREE_SLOTS)
						System.out.println("Slot: "+ slot.getId() +
								", Floor: " +  slot.getFloorId());
				}
			}
		});
		if (dtype == Display.FREE_COUNT)
			System.out.println(vacantSlotsBasedOnType.size());
		else if (dtype == Display.OCCUPIED_SLOTS)
			System.out.println(slotsBasedOnType.size() - vacantSlotsBasedOnType.size() );
	}
}
