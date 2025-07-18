package com.elemica.assignment.one;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionMapper {
	public static Map<String, String> map(List<String> arguments) {
		Map<String, String> map = new HashMap<>();
		
		for(int i = 0; i < arguments.size(); i += 2) {
			map.put(arguments.get(i), arguments.get(i+1));
		}
		
		return map;
		
		
		
		
	}
}
