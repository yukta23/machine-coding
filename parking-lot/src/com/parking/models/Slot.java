package com.parking.models;

import com.parking.utils.Type;

public class Slot {
	private int id;
	private int parkingId;
	private int floorId;
	private Type type;
	private Vehicle vehicle;
	
	public Slot(int id, int parkingId, int floorId, Type type) {
		this.id = id;
		this.parkingId = parkingId;
		this.floorId = floorId;
		this.type = type;
		this.vehicle = null;
	}

	public int getId() {
		return id;
	}

	public int getParkingId() {
		return parkingId;
	}

	public void setParkingId(int parkingId) {
		this.parkingId = parkingId;
	}

	public int getFloorId() {
		return floorId;
	}

	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isVacant() {
		return this.vehicle == null;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
}
