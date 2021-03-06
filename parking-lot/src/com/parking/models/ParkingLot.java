package com.parking.models;

import java.util.ArrayList;

public class ParkingLot {
	private int id;
	private int numSlotsPerFloor;
	private int numFloors;
	private ArrayList<Slot> parkingSlots;
	
	public ParkingLot(int parkingId, int numFloors, int numSlotsPerFloor) {
		this.id = parkingId;
		this.numFloors = numFloors;
		this.numSlotsPerFloor = numSlotsPerFloor;
		this.parkingSlots = new ArrayList<Slot>();
	}

	public int getNumFloors() {
		return numFloors;
	}

	public void setNumFloors(int numFloors) {
		this.numFloors = numFloors;
	}

	public int getNumSlotsPerFloor() {
		return numSlotsPerFloor;
	}

	public void setNumSlotsPerFloor(int numSlotsPerFloor) {
		this.numSlotsPerFloor = numSlotsPerFloor;
	}

	public ArrayList<Slot> getParkingSlots() {
		return parkingSlots;
	}

	public void setParkingSlots(ArrayList<Slot> parkingSlots) {
		this.parkingSlots = parkingSlots;
	}
	
}
