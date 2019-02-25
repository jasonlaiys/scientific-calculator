package model;

import java.util.ArrayDeque;

import util.*;

public class Calculator {
	
	private ArrayDeque<String> opStack;
	private ArrayDeque<String> numStack;
	
	public Calculator() {
		opStack = new ArrayDeque<String>();
		numStack = new ArrayDeque<String>();
	}
	
	// Copy constructor
	public Calculator(Calculator c) {
		this.opStack = c.getStack();
		this.numStack = c.getQueue();
	}
	
	public ArrayDeque<String> getStack() {
		return opStack.clone();
	}
	
	public void setStack(ArrayDeque<String> stack) {
		this.opStack = stack;
	}
	
	public ArrayDeque<String> getQueue() {
		return numStack.clone();
	}
	
	public void setQueue(ArrayDeque<String> queue) {
		this.numStack = queue;
	}
	
	public double calculate(String expression) {
		
		DynamicArray<String> component = new DynamicArray<String>();
		double answer = 0;
		
		while (expression.length() > 0) {
			if (Character.isAlphabetic(expression.charAt(0))) {		
				String temp = "";
				int i = 0;
				
				while (i < expression.length() && Character.isAlphabetic(expression.charAt(i))) {
					temp += expression.charAt(i);
					i++;
				}
				
				component.add(temp);
				expression = expression.substring(i);
			}
			
			else if (Character.isDigit(expression.charAt(0))) {
				String temp = "";
				int i = 0;
				
				while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
					temp += expression.charAt(i);
					i++;
				}
				
				component.add(temp);
				expression = expression.substring(i);
			}
			
			else {
				component.add(expression.substring(0, 1));
				expression = expression.substring(1);
			}
		}		
		
		return answer; 
	}
	
	public void numericalEval(DynamicArray<String> arr) {
		
		
		
	}
	
	public void functionEval(DynamicArray<String> arr) {
		
		
		
	}

}
