package com.parking.models;

import com.parking.utils.Color;
import com.parking.utils.Type;

public class Vehicle {
	private String registrationNumber;
	private Color color; 
	private Type type;
	
	public Vehicle(String registrationNumber, Type type, Color color) {
		this.registrationNumber = registrationNumber;
		this.type = type;
		this.color = color;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}	
}
