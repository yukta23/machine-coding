package com.datastore.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.datastore.models.DataStore;
import com.datastore.models.Pair;
import com.datastore.models.Value;

public class DataServiceImpl implements DataService {
	
	//This is the datastore, Datastore class is not needed
	private HashMap<String, Value> store = new HashMap<>();
	
	public void setDataStore(DataStore dataStore) {
		this.store = dataStore.getData_store();
	}
	
	@Override
	public Value getKey(String key) {
		if (this.store.containsKey(key))
			return this.store.get(key);
		return null;
	}

	@Override
	public ArrayList<String> searchAttributeKeyValuePair(String key, String value) {
		ArrayList<String> matchingKeys = new ArrayList<>();
		for(Map.Entry<String, Value> mapElement : this.store.entrySet()) {
			String mapKey = (String)mapElement.getKey();
			Value mapValue = (Value)mapElement.getValue();
			HashMap<String, Object> valueObj = mapValue.getValueObj();
			for(Map.Entry<String, Object> element : valueObj.entrySet()) {
				String keyattr = (String)element.getKey();
				String valueattr = (String)element.getValue();
				if (keyattr == key && valueattr == value)
					matchingKeys.add(mapKey);
				break;
			}
		}
		return matchingKeys;
	}

	@Override
	public String put(String key, ArrayList<Pair> keyValuePairs) throws Exception {
		if (this.store.containsKey(key)) { // update
			Value val = this.store.get(key);
			HashMap<String, Object> valueObj = val.getValueObj();
			for(Pair p : keyValuePairs) {
				if (valueObj.containsKey(p.first)) {
					//compare the data types
					if (valueObj.get(p.first).getClass() == p.second.getClass()) {
						valueObj.replace(p.first, p.second);
					} else {
						throw new Exception("Data type does not match");
					}
				}
			}
		} else { // add new entry
			HashMap<String, Object> valueObj = new HashMap<>();
			for(Pair p : keyValuePairs) {
				valueObj.put(p.first, p.second);
			}
			Value val = new Value(valueObj);
			this.store.put(key, val);
		}
		return key;
	}

	@Override
	public void delete(String key) throws Exception {
		if(this.store.containsKey(key)) {
			this.store.remove(key);
		} else {
			throw new Exception("Given Key doesn't exist");
		}
	}

	@Override
	public ArrayList<String> keys() {
		ArrayList<String> keys = new ArrayList<String>();
		for(Map.Entry<String, Value> mapElement : this.store.entrySet()) {
			String k = (String)mapElement.getKey(); 
			keys.add(k);
		}
		return keys;
	}
	public void printDataStore() {
		for(Map.Entry<String, Value> mapElement : this.store.entrySet()) {
			String key = (String)mapElement.getKey();
			Value val = (Value)mapElement.getValue();
			System.out.print(key.toString() + " : ");
			for(Map.Entry<String, Object> element: val.getValueObj().entrySet()) {
				String k = (String)element.getKey();
				Object v = element.getValue();
				System.out.println(k.toString() + " : " + v.toString());
			}
		}
	}
}
