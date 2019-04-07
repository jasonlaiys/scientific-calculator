/*
 * 
 * Author: Jason Lai
 * 
 */


package model;



import adapter.*;
import util.*;



import java.math.*;



public class FunctionEvaluator implements FunctionsList {
	
	private DynamicArray<Token> expression;
	private Token answer;
	private NumericalEvaluator numeval;
	private FunctionEvaluator fneval;
	
	
	public FunctionEvaluator(DynamicArray<Token> exp) {
		expression = exp;
		evaluate();
	}
	
	public DynamicArray<Token> getExp() {
		return expression;
	}
	
	public Token getAnswer() {
		return answer;
	}
	
	/*
	 * 
	 * PRIVATE MEMBER FUNCTIONS
	 * These functions handle basic and nested function expressions
	 * 
	 */
	
	private void evaluate() {
		
		
		
		
	}
	
	
	
	/*
	 * 
	 * PRIVATE MEMBER FUNCTIONS
	 * These functions handle the evaluation of the predefined functions
	 * 
	 */
	
	private String sin(Token t) {
		BigDecimal max = new BigDecimal(360);
		BigDecimal num = new BigDecimal(t.getData());
		
		while (num.compareTo(max) == 1) {
			num.subtract(max);
		}
		
		double d = num.doubleValue();
		double ans = Math.sin(d);
		
		
		return Double.toString(ans);
	}
	
	private String cos(Token t) {
		BigDecimal max = new BigDecimal(360);
		BigDecimal num = new BigDecimal(t.getData());
		
		while (num.compareTo(max) == 1) {
			num.subtract(max);
		}
		
		double d = num.doubleValue();
		double ans = Math.cos(d);
		
		
		return Double.toString(ans);
	}
	
	private String tan(Token t) {
		BigDecimal max = new BigDecimal(360);
		BigDecimal num = new BigDecimal(t.getData());
		
		while (num.compareTo(max) == 1) {
			num.subtract(max);
		}
		
		double d = num.doubleValue();
		double ans = Math.tan(d);
		
		
		return Double.toString(ans);
	}
	
	

}
