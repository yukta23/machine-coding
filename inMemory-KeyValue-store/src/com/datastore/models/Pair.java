package com.datastore.models;

public class Pair {
	public String first;
	public Object second; // to store String, Integer, Double or Boolean
	public Pair(String first, Object second) {
		this.first = first;
		this.second = second;
	}
}
