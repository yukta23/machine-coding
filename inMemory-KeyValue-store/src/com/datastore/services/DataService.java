package com.datastore.services;

import java.util.ArrayList;

import com.datastore.models.Pair;
import com.datastore.models.Value;

public interface DataService {
	//Should return the value (object with attributes and their values). Return null if key not present
	Value getKey(String key);
	//Returns a list of keys that have the given attribute key, value pair.
	ArrayList<String> searchAttributeKeyValuePair(String key, String value);
	
	//Adds the key and the attributes to the key-value store. 
	//If the key already exists then the value is replaced.
	String put(String key, ArrayList<Pair> keyValuePairs) throws Exception;
	//deletes key-value pair from the store
	void delete(String key) throws Exception;
	
	//returns list of all keys
	ArrayList<String> keys();
}
