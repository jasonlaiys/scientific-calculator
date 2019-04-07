package adapter;

import util.*;

public interface Solvable {

	public DynamicArray<Token> getExpression();
	public void evaluate(String input);
	public String getAnswer();
	
}
