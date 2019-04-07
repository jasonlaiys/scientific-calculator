/*
 * 
 * Author: Jason Lai
 * 
 */


package model;

import util.*; 

import java.math.BigDecimal;
import java.math.MathContext;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumericalEvaluator {
	
	/*
	 * 
	 * ATTRIBUTES / INSTANCE VARIABLES
	 * 
	 */
	private DynamicArray<Token> infixExp;
	private DynamicArray<Token> postfixExp = new DynamicArray<Token>();
	private Deque<Token> tokenStack = new ArrayDeque<Token>();
	private Deque<BigDecimal> bdStack = new ArrayDeque<BigDecimal>();
	private Token result;
	
	
	
	/*
	 * 
	 * CONSTRUCTORS, GETTERS, SETTERS
	 * 
	 */
	public NumericalEvaluator(DynamicArray<Token> da) {
		infixExp = da;
		convertPostfix();
		evaluate();
	}
	
	public DynamicArray<Token> getInfixExp() {
		return infixExp;
	}
	
	public DynamicArray<Token> getPostfixExp() {
		return postfixExp;
	}
	
	public Token getResult() {
		return result;
	}
	
	
	
	/*
	 * 
	 * PRIVATE MEMBER FUNCTIONS
	 * This function evaluates a numerical expression
	 * 
	 */
	private void evaluate() {
		
		for (int i = 0; i < postfixExp.getSize(); i++) {
			
			if (postfixExp.get(i).getType().equals("NUMBER")) {
				BigDecimal dec = new BigDecimal(postfixExp.get(i).getData());
				bdStack.addFirst(dec);
			}
			
			else {
				BigDecimal temp = bdStack.removeFirst();
				BigDecimal res = bdStack.removeFirst();
				
				switch (postfixExp.get(i).getData()) {
					
				case "+":
					res = res.add(temp);
					break;
					
				case "-":
					res = res.subtract(temp);
					break;
					
				case "*":
					res = res.multiply(temp);
					break;
					
				case "/":
					res = res.divide(temp, MathContext.DECIMAL64);
					break;
					
				}
				
				bdStack.addFirst(res);
				
			}
			
		}
		
		result = new Token(bdStack.removeFirst().toPlainString());
		
	}
	
	
	
	/*
	 * 
	 * PRIVATE MEMBER FUNCTIONS
	 * These functions convert infix notation into postfix notation for numerical evaluation
	 * 
	 */
	
	private void convertPostfix() {	
		
		for (int i = 0; i < infixExp.getSize(); i++) {
			
			if (infixExp.get(i).getType().equals("NUMBER")) {
				postfixExp.add(infixExp.get(i));
			}
			
			else {
				pushOntoStack(infixExp.get(i));
			}
			
		}
		
		clearStack();
		
	}
	
	private void pushOntoStack(Token token) {
		
		if (tokenStack.isEmpty() || token.getData().equals("(")) {
			tokenStack.addFirst(token);
		}
		
		else {
			
			if (token.getData().equals(")")) {
				
				while (!tokenStack.getFirst().getData().equals("(")) {
					postfixExp.add(tokenStack.removeFirst());
				}
				
				tokenStack.removeFirst();
			}
			
			else {
				
				if (tokenStack.getFirst().getData().equals("(")) {
					tokenStack.addFirst(token);
				}
				
				else {
					
					while (!tokenStack.isEmpty() && !tokenStack.getFirst().getData().equals("(") && getOpPrecedence(token) <= getOpPrecedence(tokenStack.getFirst())) {
						postfixExp.add(tokenStack.removeFirst());
					}
					
					tokenStack.addFirst(token);
				}
				
			}
			
		}
		
	}
	
	private void clearStack() {
		
		while (!tokenStack.isEmpty()) {
			postfixExp.add(tokenStack.removeFirst());
		}
		
	}
	
	private int getOpPrecedence(Token op) {
		
		switch(op.getData()) {
		
			case "+":
				
			case "-":
				return 1;
				
			case "*":
				
			case "/":
				return 2;
				
			default:
				return 0;
				
		}
		
	}

}
