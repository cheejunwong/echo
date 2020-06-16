package math;

import java.net.*;
import echo.*;

public class MathHandler extends RequestHandler {
	
	private double total;
	
	public MathHandler(Socket sock) {
		super(sock);
		init();
	}

	public MathHandler() {
		super();
		init();
	}

	private void init() {
		total = 0.0;
	}
	
	protected String response(String request) throws Exception {
		String result = "";
		
		String[] requestArray = request.split(" ");
		
		if (requestArray[0].equalsIgnoreCase("help")) {
			return "commands: add, mul, sub, div, and help";
		}
		
		if (requestArray.length == 1) {
			return "No operand";
		}
		
		if (requestArray[0].equalsIgnoreCase("add")) {
			init();
			for(int i = 1; i < requestArray.length ; i++) {
				total = total + Double.parseDouble(requestArray[i]);
			}
			result += total;
			
		} else if (requestArray[0].equalsIgnoreCase("mul")) {
			total = 1.0;
			for(int i = 1; i < requestArray.length ; i++) {
				total = total * Double.parseDouble(requestArray[i]);
			}
			result += total;
			
		} else if (requestArray[0].equalsIgnoreCase("sub")) {
			init();
			if (requestArray.length == 2)
				total = total - Double.parseDouble(requestArray[1]);
			
			else {
				total = Double.parseDouble(requestArray[1]);
				for (int i = 2; i < requestArray.length ; i++) {
					total = total - Double.parseDouble(requestArray[i]);
				}
			}
			result += total;
			
		} else if (requestArray[0].equalsIgnoreCase("div")) {
			init();
			if (requestArray.length == 2) {
				return "Not enough operand";
			}
			
			double array1 = Double.parseDouble(requestArray[1]);
			if (array1 == 0.0) {
				return "Invalid (Div by zero)";
			
			}
			
			total = total + array1;
			for(int i = 2; i < requestArray.length ; i++) {
				total = total / Double.parseDouble(requestArray[i]);
							
			}
			result += total;
			
			
		} else {
			result = "Unrecognized command: " + request;
		}
		return result;
	}

}
