package com.datastore.main;

import java.util.ArrayList;

import com.datastore.models.Pair;
import com.datastore.services.DataServiceImpl;

public class Main {

	public static void main(String[] args) {
		DataServiceImpl service = new DataServiceImpl();
		if(args.length == 0) {
			System.out.println("Give some command line arguments");
			return;
		}
		int i = 0;
		while( i < args.length ) {
			String command = args[i++];
			switch(command) {
				case "get" : {
					String key = args[i++];
					System.out.println(service.getKey(key).toString());
					break;
				}
				case "put" : {
					String key = args[i++];
					int count = 0;
					ArrayList<Pair> pairs = new ArrayList<>();
					while(count < 3) {
						String keyattr = args[i++];
						String valueattr = args[i++];
						pairs.add(new Pair(keyattr, valueattr));
						count++;
					}
					service.put(key, pairs);
					break;
				}
				case "delete": {
					String key = args[i++];
					try {
						service.delete(key);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				case "keys": {
					ArrayList<String> keys = service.keys();
					if(keys.size() == 0) {
						System.out.println("Keys don't exist");
						break;
					}
					for(String key : keys) {
						System.out.println(key);
					}
					break;
				}
			}
		}
	}
}
