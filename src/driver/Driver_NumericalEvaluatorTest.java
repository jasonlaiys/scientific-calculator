package driver;

import model.*;

public class Driver_NumericalEvaluatorTest {
	
	public static void main(String[] args) {
		
		Calculator c = new Calculator("3.134213+(51-434)/2+1651.46851*2.5");
		System.out.println(c.getExpression().toString());
		c.numEvaluate();
		System.out.println(c.getAnswer());
		
	}

}
