package com.elemica.assignment.one;

import java.util.List;

public class CheckArgument {
	public static List<String> checkArgument(String input) throws Exception {
		List<String> arguments = List.of(input.trim().split("\\s+")); //treats extra spaces as one.
		
		//	**check if name is the first option
		if (!arguments.get(0).equals("-name")) {
            throw new Exception("Invalid Argument: Must start with -name");
        }
		
		// **check if every 2nd argument is an option
		List<String> validOptions = List.of("-name", "-type", "-quantity", "-price");
		for(int i = 0; i < arguments.size(); i += 2) {
			if(-1 == validOptions.indexOf(arguments.get(i))) throw new Exception("Invalid Argument: " + arguments.get(i) + " is not a valid option");
		}
		
		for(String arg : validOptions) {
			if(-1 == arguments.indexOf(arg)) {
				throw new Exception("Option " + arg + " not found!");
			}
		}
		
		return arguments;
	}
}
