package com.datastore.main;

import java.util.ArrayList;

import com.datastore.models.Pair;
import com.datastore.services.DataServiceImpl;

public class Main {
	public static DataServiceImpl service = new DataServiceImpl();
	public static void main(String[] args) {
		// Command Line Utility
		ArrayList<String> commands = new ArrayList<String>() {
			{
				add("put");
				add("get");
				add("delete");
				add("search");
				add("keys");
			}
		};
		ArrayList<String> commandStatement = new ArrayList<>();
		String prevArg = "";
		for(String arg : args ) {
			// command arg
			if (search(commands, arg)) {
				if (commandStatement.size() != 0 )
				{
					processCommand(prevArg, commandStatement);
				}
				prevArg = arg;
				commandStatement.clear();
			} else 
			{
				commandStatement.add(arg);
			}
		}
		if (commandStatement.size() != 0 )
		{
			processCommand(prevArg, commandStatement);
		}
	}
	public static boolean search(ArrayList<String> commands,String arg) {
		for( int i = 0; i < commands.size(); i++ ) {
			if (commands.get(i).equalsIgnoreCase(arg))
				return true;
		}
		return false;
	}
	public static void processCommand(String command, ArrayList<String> statement) {
		switch(command) {
			case "get" : {
				String key = statement.get(0);
				if (service.getKey(key) == null ) {
					System.out.println("No entry found for " + key);
				} else {
					System.out.println(service.getKey(key).toString());
				}
				break;
			}
			case "put": {
				String key = statement.get(0);
				ArrayList<Pair> pairs = new ArrayList<Pair>();
				for( int i = 1; i < statement.size(); i += 2 ) {
					String k = statement.get(i);
					String v = statement.get(i + 1);
					if (v.equalsIgnoreCase("true") || v.equalsIgnoreCase("false"))
						pairs.add(new Pair(k, Boolean.parseBoolean(v)));
					else if (v.matches("[+-]?([0-9]*[.])?[0-9]+"))
						pairs.add(new Pair(k, Double.parseDouble(v)));
					else if (v.matches("^[0-9]{1,45}$"))
						pairs.add(new Pair(k, Integer.parseInt(v)));
					else 
						pairs.add(new Pair(k, v));	
				}
				try {
					service.put(key, pairs);
				} catch (Exception e) {
					System.out.println("Datatype Error");
				}
				break;
			}
			case "delete": {
				String key = statement.get(0);
				try {
					service.delete(key);
				} catch (Exception e) {
					System.out.println("Data Type Error");
				}
				break;
			}
			case "keys": {
				ArrayList<String> keys = service.keys();
				for(String key : keys) {
					System.out.print(key + ",");
				}
				System.out.println();
				break;
			}
			case "search": {
				String key = statement.get(0);
				String v = statement.get(1);
				Object o = null;
				try {
					o = Integer.parseInt(v);
				} catch(Exception a) {
					try {
						o = Double.parseDouble(v);
					} catch(Exception b) {
						try {
							o = Boolean.parseBoolean(v);
						} catch(Exception c) {
							o = v;
						}
					}
				}
				ArrayList<String> keys = service.searchAttributeKeyValuePair(key, v);
				for(String k : keys) {
					System.out.print(k + ",");
				}
				System.out.println();
				break;
			}
		}
	}
}
