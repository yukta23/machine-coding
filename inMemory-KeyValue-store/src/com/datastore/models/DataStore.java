package com.datastore.models;

import java.util.HashMap;

public class DataStore {
	private HashMap<String, Value> data_store;
	public DataStore() {
		this.data_store = new HashMap<>();
	}
	public DataStore(HashMap<String, Value> store) {
		this.data_store = store;
	}

	public HashMap<String, Value> getData_store() {
		return data_store;
	}

	public void setData_store(HashMap<String, Value> data_store) {
		this.data_store = data_store;
	}
}
