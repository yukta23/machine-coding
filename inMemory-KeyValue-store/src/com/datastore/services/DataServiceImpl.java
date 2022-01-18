package com.datastore.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.datastore.models.DataStore;
import com.datastore.models.Pair;
import com.datastore.models.Value;

public class DataServiceImpl implements DataService {
	
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
	public String put(String key, ArrayList<Pair> keyValuePairs) {
		HashMap<String, Object> valueObj = new HashMap<>();
		for(Pair p : keyValuePairs) {
			if(!valueObj.containsKey(p.first)) {
				valueObj.put(p.first, p.second);
			}
		}
		Value val = new Value(valueObj);
		if (this.store.containsKey(key)) {
			this.store.replace(key, val);
		} else {
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

}
