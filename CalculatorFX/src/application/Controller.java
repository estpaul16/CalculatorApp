package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * @author evanst.paul
 * This is the controller class of my CalculatorFX app. It handles all
 * interactions between the UI and Model, such has how to handle each button 
 */
public class Controller {
	
	@FXML private Label result;
	
	/**
	 * Need this as a field so we can change switch text between AC and C
	 */
	@FXML private Button clearButton;
	
	/**
	 * Used to show the entire result output when the length exceeds that of label boundaries
	 */
	@FXML private Tooltip fullResult;
	
	
	private double num1 = 0;
	private double num2;
	private String operator = "";
	private Model calcModel = new Model();
	
	/**
	 * numIsFinished - set true when operator button is pressed, signifying
	 * all digits of desired input number have been entered
	 */
	private boolean numIsFinished = true; 
	
	/**
	 * Needed for feature that let's the user continuously calculate 
	 * on the same operator and second input value by pressing the equals button back to 
	 * back (ex. Entering 3+2 to get five, then equals again and again to get 5+2+2+2 ...).
	 * We need this variable to avoid reassigning num2 to what is in the result label if no
	 * new value has been added since the last equals call
	 */
	private boolean num2IsOld = false;
	
	/**
	 * handler for all number keys
	 */
	@FXML
	public void handleNumKeys(ActionEvent e) {
		if (numIsFinished) {
			result.setText("");
			numIsFinished = false;
			num2IsOld = false;
			clearButton.setText("C");
		}
		
		String selectedDigit = ((Button)e.getSource()).getText();
		result.setText(result.getText() + selectedDigit);
		fullResult.setText(result.getText());
		
		
	}
	
	/**
	 * handler for all operators (+, -, x, ÷, =)
	 */
	@FXML
	public void handleOperatorKeys(ActionEvent e) {
		String selectedOp = ((Button)e.getSource()).getText();
		if (!selectedOp.equals("=")) {
			operator = selectedOp;
			numIsFinished = true;			
			num1 = Double.parseDouble(result.getText());
//			num2 = num1;		//for the case when equals is hit right after operator
		} else {
			if (!num2IsOld) {
				num2 = Double.parseDouble(result.getText());
			}
			String output = calcModel.calculate(num1, num2, operator);
			num1 = Double.parseDouble(output);	// Clicking "=" again will compute 'output (op) num2 =...
			result.setText(displayAsDoubleOrInt(num1));
			numIsFinished = true;
			num2IsOld = true;
			
		}
	}
	
	@FXML
	public void handleDecimalKey() {
		if (numIsFinished) {
			result.setText("0");
			numIsFinished = false;
			num2IsOld = false;
			clearButton.setText("C");
		}
		String currentResult = result.getText();
		// If there is already a decimal in the number then do nothing
		if (currentResult.indexOf('.') != -1) return;
		result.setText(currentResult + ".");	
	}
	
	@FXML
	public void handleClearKey() {
		clearButton.setText("AC");
		result.setText("0");
		fullResult.setText("0");
		numIsFinished = true;	
	}
	
	@FXML public void handleTimesNegOneKey() {
		double output = Double.parseDouble(result.getText());
		output = output * (-1);
		result.setText(displayAsDoubleOrInt(output));
		numIsFinished = true;
	}
	
	@FXML public void handlePercentKey() {
		double output = Double.parseDouble(result.getText());
		output = output / 100;
		result.setText(displayAsDoubleOrInt(output));
		numIsFinished = true;
	}
	
	// If the result is an integer, display it as an integer
	private String displayAsDoubleOrInt(double num) {
		String outputText = num + "";
		if (outputText.charAt(outputText.length() - 2) == '.') {
			return ((int) num) + "";
		} else {
			return outputText;
		}
	}
}
