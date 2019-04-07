/*
 * 
 * Author: Jason Lai
 * 
 */


package model;

import adapter.*;
import util.*;

public class Calculator implements Solvable, FunctionsList {
	
	private Tokenizer tk;
	private NumericalEvaluator numeval;
	private FunctionEvaluator fneval;
	private DynamicArray<Token> expression;
	private String result;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public Calculator() {
		tk = new Tokenizer();
	}
	
	public Calculator(String input) {
		tk = new Tokenizer(input);
		expression = tk.getExpression();
	}
	
	/*
	 * 
	 * PUBLIC MEMBER FUNCTIONS
	 * 
	 */
	
	public DynamicArray<Token> getExpression() {
		return expression;
	}
	
	public String getAnswer() {
		return result;
	}
	
	public void evaluate() {
		
		validate();
		
		DynamicArray<Token> exp = expression;
		
		while (hasFunctions()) {
			splitFnEval(exp);
		}
		
		numEvaluate();	

	}
	
	public void evaluate(String input) {
		
		tk = new Tokenizer(input);
		expression = tk.getExpression();
		evaluate();
		
	}
	
	public boolean hasFunctions() {
		
		for (int i = 0; i < expression.getSize(); i++) {
			if (expression.get(i).getType().equals("FUNCTION"))
				return true;
		}
		
		return false;
	}
	
	public void splitFnEval(DynamicArray<Token> exp) {
		
		DynamicArray<Token> subexp = new DynamicArray<Token>();
		int index = -1;
		int endIndex = 0;
		
		for (int i = 0; i < exp.getSize(); i++) {
			if (exp.get(i).getType().equals("FUNCTION")) {				
				index = i;
				break;
			}
		}
			
		int beginIndex = index;
		
		while (beginIndex > 0 && (!exp.get(index).getType().equals("OPERATOR") || !exp.get(index).getType().equals("PARANTHESES"))) {
			beginIndex --;
		}
		
		index++;
		
		for (int i = beginIndex; i < index; i++) {
			subexp.add(exp.get(i));
		}
		
		do {
			
			int balfactor = 0;
			do {
				
				subexp.add(exp.get(index));
				
				if (exp.get(index).getData().equals("(")) {
					balfactor++;					
				}
				
				if (exp.get(index).getData().equals(")")) {
					balfactor--;
				}
						
				index++;	
				
			} while (balfactor != 0);
			
		} while (index < exp.getSize() && !exp.get(index).getType().equals("OPERATOR"));
		
		endIndex = index;
		
		Token t = fnEvaluate(subexp);
		exp.merge(t, beginIndex, endIndex);
		
	}
	
	
	
	/*
	 * 
	 * These functions validate the given expression by balancing
	 * parentheses and resolving unconventional mathematical
	 * operations into conventional mathematical operations
	 * 
	 */
	
	public void validate() {
		
		parenBalance();
		resolveChainOp();
		resolveBlockedOp();
		resolveParenCoefficients();
		
	}
	
	public void parenBalance() {
		
		int balfactor = 0;
		
		for (int i = 0; i < expression.getSize(); i++) {
			if (expression.get(i).getData().equals("(")) {
				balfactor++;
			}
			
			if (expression.get(i).getData().equals(")")) {
				balfactor--;
				if (expression.get(i + 1).getType().equals("NUMBER")) {
					throw new IllegalArgumentException("Syntax error! Number found after parantheses.");
				}
			}
		}
		
		if (balfactor > 0) {
			Token t = new Token(")");
			while (balfactor != 0) {
				expression.add(t);
				balfactor--;
			}
		}
		
		else if (balfactor < 0) {
			throw new IllegalArgumentException("Syntax error! Expression not balanced.");
		}
		
	}
	
	public void resolveChainOp() {
		
		int index = 0;
		int beginIndex = -1;
		int endIndex = 0;
		boolean eol = false;
		StringBuilder sb = new StringBuilder();
		String resolved_op = "";
		
		while (!eol) {
			
			if (expression.get(index).getType().equals("OPERATOR")) {
				beginIndex = index;
				sb.append(expression.get(index).toString());
				
				for (int i = beginIndex + 1; i < expression.getSize(); i++) {					
					if (!expression.get(i).getType().equals("OPERATOR")) {
						break;
					}
					
					sb.append(expression.get(i).toString());
					endIndex = i;
				}
				
				if (sb.length() > 1) {
					
					int add_operator = 0;
					int sub_operator = 0;
					int commutative_operators = 0;
					
					for (int i = 0; i < sb.length(); i++) {
						if (((Character)sb.charAt(i)).toString().equals("+")) {
							add_operator++;
						}
						
						else if (((Character)sb.charAt(i)).toString().equals("-")) {
							sub_operator++;
						}
						
						else {
							commutative_operators++;
						}
						
						if (commutative_operators > 1) {
							throw new IllegalArgumentException("Syntax error! Chained commutative operators found.");
						}
					}
					
					if (sub_operator % 2 == 0) {
						resolved_op = "+";
					}
					
					else {
						resolved_op = "-";
					}
					
					expression.merge(new Token(resolved_op), beginIndex, endIndex + 1);
					
				}
				
				resolved_op = "";
				sb.delete(0, sb.length());
				endIndex++;
				index = endIndex;
				
			}
			
			else {
				index++;
			}
			
			if (index >= expression.getSize()) {
				eol = true;
			}
			
		}
		
	}
	
	public void resolveBlockedOp() {
		
		for (int i = 0; i < expression.getSize(); i++) {
			if (expression.get(i).getData().equals("(")) {
				if (expression.get(i + 1).getData().equals("+") || expression.get(i + 1).getData().equals("-")) {
					expression.addPushBack(new Token("0"), i + 1);
				}
				
				else if (expression.get(i + 1).getData().equals("*") || expression.get(i + 1).getData().equals("/")) {
					throw new IllegalArgumentException("Syntax error! Commutative operator found after opening parentheses.");
				}
				
				else {
					;
				}
			}
			
			if (expression.get(i).getType().equals("OPERATOR")) {
				if (expression.get(i + 1).getData().equals(")")) {
					throw new IllegalArgumentException("Syntax error! Operator found before closing parentheses.");
				}
			}
		}
		
	}
	
	
	
	public void resolveParenCoefficients() {
		
		for (int i = 0; i < expression.getSize(); i++) {
			if (expression.get(i).getType().equals("NUMBER") && i != expression.getSize() - 1) {
				if (expression.get(i + 1).getData().equals("(")) {
					expression.addPushBack(new Token("*"), i + 1);
				}
			}
		}
		
	}
	
	
	
	/*
	 * 
	 * These functions use instances of FunctionEvaluator and
	 * NumericalEvaluator to calculate the answer
	 * 
	 */
	
	public Token fnEvaluate(DynamicArray<Token> subexp) {
		fneval = new FunctionEvaluator(subexp);	
		return fneval.getAnswer();
	}
	
	public void numEvaluate() {
		numeval = new NumericalEvaluator(expression);
		result = numeval.getResult().getData();
	}

	
	

}
