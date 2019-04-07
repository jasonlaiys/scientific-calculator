package driver;

import model.*;
import adapter.*;

public class Driver_NumericalEvaluatorTest {
	
	public static void main(String[] args) {
		
		Solvable s1 = new Calculator();
		
		s1.evaluate("3.134213+(51-434)/2+1651.46851*2.5");
		System.out.println(s1.getExpression().toString());
		System.out.println(s1.getAnswer() + "\n");
		
		s1.evaluate("(50*2+30*5)/2.5");
		System.out.println(s1.getExpression().toString());
		System.out.println(s1.getAnswer() + "\n");
		
		System.out.println("========== RESOLVE CHAINED OPERATOR TEST ==========");
		s1.evaluate("(50*2+---++++-+30*5)/2.5");
		System.out.println(s1.getExpression().toString());
		System.out.println(s1.getAnswer() + "\n");
		
		System.out.println("========== RESOLVE BLOCKED OPERATOR TEST ==========");
		s1.evaluate("(-50*2+30*5)/2.5");
		System.out.println(s1.getExpression().toString());
		System.out.println(s1.getAnswer() + "\n");
		
		System.out.println("========== RESOLVE NUMERICAL COEFFICIENTS TEST ==========");
		s1.evaluate("2(-50*2+30*5)/2.5");
		System.out.println(s1.getExpression().toString());
		System.out.println(s1.getAnswer() + "\n");
		
	}

}
