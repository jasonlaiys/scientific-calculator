package driver;

import model.*;
import adapter.*;

public class Driver_NumericalEvaluatorTest {
	
	public static void main(String[] args) {
		
		Calculator c1 = new Calculator("3.134213+(51-434)/2+1651.46851*2.5");
		System.out.println(c1.getExpression().toString());
		c1.evaluate();
		System.out.println(c1.getAnswer() + "\n");
		
		Calculator c2 = new Calculator("(50*2+30*5)/2.5");
		System.out.println(c2.getExpression().toString());
		c2.evaluate();
		System.out.println(c2.getAnswer() + "\n");
		
		System.out.println("========== RESOLVE CHAINED OPERATOR TEST ==========");
		Calculator c3 = new Calculator("(50*2+---++++-+30*5)/2.5");
		System.out.println(c3.getExpression().toString());
		c3.evaluate();
		System.out.println(c3.getExpression().toString());
		System.out.println(c3.getAnswer() + "\n");
		
		Solvable s1 = new Calculator();
		s1.evaluate("(50*2+30*5)/2.5");
		
//		System.out.println("========== RESOLVE BLOCKED OPERATOR TEST ==========");
//		Calculator c4= new Calculator("(-50*2+30*5)/2.5");
//		System.out.println(c4.getExpression().toString());
//		c4.evaluate();
//		System.out.println(c4.getExpression().toString());
//		System.out.println(c4.getAnswer() + "\n");
		
	}

}
