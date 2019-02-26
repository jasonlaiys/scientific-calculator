package model;

import util.*;

public class Calculator {
	
	private Tokenizer tk;
	private NumericalEvaluator numeval;
	private FunctionEvaluator fneval;
	private DynamicArray<Token> expression;
	private String result;
	
	public Calculator() {
		tk = new Tokenizer();
	}
	
	public Calculator(String input) {
		tk = new Tokenizer(input);
		expression = tk.getExpression();
	}
	
	public DynamicArray<Token> getExpression() {
		return expression;
	}
	
	public String getAnswer() {
		return result;
	}
	
	public void fnEvaluate() {
		
	}
	
	public void numEvaluate() {
		numeval = new NumericalEvaluator(expression);
		result = numeval.getResult().getData();
	}

	
	

}
