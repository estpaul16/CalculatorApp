package application;

/**
 * @author evanst.paul
 * This is the model class of my CalculatorFX app. It handles all the data being stored,
 * as well as how it is manipulated
 */
public class Model {
	
	/**
	 * Handles all possible calculations
	 * @param num1 - the first number being operated on
	 * @param num2 - the second number being operated on
	 * @param operator - the operator being called on num1 and num2 
	 * @return calculate - result of operation in String format rather than 
	 * double because of the ÷0 case on Mac calculator displaying a String
	 */
	public String calculate(double num1, double num2, String operator) {
		switch (operator) {
		case "+":
			return num1 + num2 + "";
		case "-":
			return num1 - num2 + "";
		case "x":
			return num1 * num2 + "";
		case "÷":
			if (num2 == 0) return "Not a number";
			return (num1 / num2) + "";
		default:
			return"0";
		}
	}
}
