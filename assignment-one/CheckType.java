package com.elemica.assignment.one;

import java.util.List;

public class CheckType {
	public static void checkType(String input) throws Exception {
		List<String> validTypes = List.of("raw", "manufactured", "imported");
		
		if (-1 == validTypes.indexOf(input)) {
            throw new Exception("Invalid type: Item type must be ");
        }
	}
}
