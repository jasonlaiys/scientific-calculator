package util;

import java.util.InputMismatchException;

public class Token {
	
	/*
	 * 
	 * ENUMERATORS
	 * This enumerator represents the only types of data a token can be 
	 * 
	 */
	private enum Type { 
		FUNCTION, 
		OPERATOR, 
		NUMBER, 
		NIL;
	}
	
	
	
	/*
	 * 
	 * IMMUTABLE SET OF DEFINED OPERATORS AND FUNCTIONS
	 * 
	 */
	private final String[] operators = {
			"+",
			"-",
			"*",
			"/",
			"(",
			")"
	};
	
	private final String[] functions = { 
			"^",
			"sin",
			"cos",
			"tan",
			"arcsin",
			"arccos",
			"arctan"
	};
	
	
	
	/*
	 * 
	 * MUTABLE ATTRIBUTES / INSTANCE VARIABLES
	 * 
	 */
	private String type;
	private String data; // maybe set it to string and take care of type cast in calling function
	
	
	
	/*
	 * 
	 * CONSTRUCTORS, GETTERS, SETTERS
	 * 
	 */
	public Token() {
		data = null;
		type = Type.NIL.name();
	}
	
	public Token(String data) {
		setData(data);
	}
	
	public Token(Token t) {
		type = t.getType();
		data = t.getData();
	}
	
	public String getType() {
		return type;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		if (isOperator(data)) {
			this.data = data;
			type = Type.OPERATOR.name();
		}
		
		else if (isFunction(data)) {
			this.data = data;
			type = Type.FUNCTION.name();
		}
		
		else if (isNumber(data)) {
			this.data = data;
			type = Type.NUMBER.name();
		}
		
		else {
			throw new InputMismatchException("Invalid token! Token \"" + data + "\" does not match any operator or function and is not a valid number.");
		}
	}
	
	public String toString() {
		return data;
	}
	
	
	
	/*
	 * 
	 * PRIVATE MEMBER FUNCTIONS
	 * These functions determine the data's "type" 
	 * 
	 */
	private boolean isOperator(String data) {
		for (int i = 0; i < operators.length; i++) {
			if (data.equals(operators[i])) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isFunction(String data) {
		for (int i = 0; i < functions.length; i++) {
			if (data.equals(functions[i])) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isNumber(String data) {
		for (int i = 0; i < data.length(); i++) {
			if (!Character.isDigit(data.charAt(i)) && data.charAt(i) != '.') {
				return false;
			}
		}
		return true;
	}

}
