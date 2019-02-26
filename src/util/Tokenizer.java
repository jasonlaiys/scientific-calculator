package util;

public class Tokenizer {
	
	private DynamicArray<Token> expression;
	
	public Tokenizer() {
		expression = new DynamicArray<Token>();
	}
	
	public Tokenizer(String input) {
		expression = new DynamicArray<Token>();
		parseInput(input);
	}
	
	public Tokenizer(Tokenizer t) {
		expression = t.getExpression();
	}
	
	public DynamicArray<Token> getExpression() {
		return expression;
	}
	
	public void parseInput(String input) {
		while (input.length() > 0) {
			if (Character.isAlphabetic(input.charAt(0))) {		
				String temp = "";
				int i = 0;
				
				while (i < input.length() && Character.isAlphabetic(input.charAt(i))) {
					temp += input.charAt(i);
					i++;
				}
				
				Token t = new Token(temp);
				expression.add(t);
				input = input.substring(i);
			}
			
			else if (Character.isDigit(input.charAt(0)) || input.charAt(0) == '.') {
				String temp = "";
				int i = 0;
				
				while (i < input.length() && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
					temp += input.charAt(i);
					i++;
				}
				
				Token t = new Token(temp);
				expression.add(t);
				input = input.substring(i);
			}
			
			else {
				Token t = new Token(input.substring(0, 1));
				expression.add(t);
				input = input.substring(1);
			}
		}		
	}

}
