package driver;

import util.*;

public class Driver_TokenizerTest {
	
	public static void main(String[] args) {
		Tokenizer t = new Tokenizer("2+(39.25321-sin(arctan1))+82.153/23*4.3151");
		DynamicArray<Token> e = t.getExpression();
		
		for (int i = 0; i< e.getSize(); i++) {
			System.out.println(e.get(i).getData());
		}
	}

}
