package com.datastore.models;

import java.util.HashMap;
import java.util.Map;

public class Value {
	private HashMap<String, Object> valueObj;
	
	public Value() {
		this.valueObj = new HashMap<>(); 
	}
	public Value(HashMap<String, Object> obj) {
		this.valueObj = obj;
	}
	public HashMap<String, Object> getValueObj() {
		return valueObj;
	}
	public void setValueObj(HashMap<String, Object> valueObj) {
		this.valueObj = valueObj;
	}
	@Override
	public String toString() {
		String stringConv = "";
		for(Map.Entry<String, Object> element : this.valueObj.entrySet()) {
			String key = (String)element.getKey();
			stringConv += key;
			stringConv +=":";
			String value = (String)element.getValue();
			stringConv += value;
			stringConv += ",";
		}
		return stringConv;
	}
	
}
