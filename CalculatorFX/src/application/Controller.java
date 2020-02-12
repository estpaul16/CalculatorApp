package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
	
	@FXML private Label result;
	
	private double num1 = 0;
	private double num2;
	private String operator = "";
	private Model calcModel = new Model();
	/**
	 * equalsPressed - set true after "=" operator pressed, so other
	 * operators know if they should perform their equals function.
	 * Check readMe for specific Mac Calculator features
	 */
	private boolean equalsPressed = true;
	
	/**
	 * numIsFinished - set true when operator button is pressed, signifying
	 * all digits of desired input number have been entered
	 */
	private boolean numIsFinished = true; 
	
	/**
	 * handler for all number keys
	 * @param e - the number button being pressed
	 */
	@FXML
	public void handleNumKeys(ActionEvent e) {
		if (numIsFinished) {
			result.setText("");
			numIsFinished = false;
		}
		
		String selectedDigit = ((Button)e.getSource()).getText();
		result.setText(result.getText() + selectedDigit);
	}
	
	/**
	 * handler for all operators (+, -, x, ÷, =)
	 * @param e - the operator button being pressed
	 */
	@FXML
	public void handleOperatorKeys(ActionEvent e) {
		String selectedOp = ((Button)e.getSource()).getText();
		if (!selectedOp.equals("=")) {
			operator = selectedOp;
			numIsFinished = true;			
			num1 = Double.parseDouble(result.getText());
			num2 = num1;
		} else {
			num2 = Double.parseDouble(result.getText());
			String output = calcModel.calculate(num1, num2, operator);
			result.setText(output);
			equalsPressed = true;
			numIsFinished = true;
			
		}
	}
}
