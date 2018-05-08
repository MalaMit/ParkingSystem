package dataValidation;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DataValidation {

    public static boolean textAlphabetWithPolishMarks(TextField inputTextField, Label inputLabel, String validationText, String requiredLength) {
        boolean isCorrect = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[a-zA-ZąęćżźńłóśĄĆĘŁŃÓŚŹŻ]{2,"+ requiredLength +"}")) {
        	isCorrect = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        System.out.println(inputTextField.getText().matches("[a-zA-ZąęćżźńłóśĄĆĘŁŃÓŚŹŻ]{2,20}"));
        return isCorrect;

    }

	public static boolean textAlphabetAndNumber(TextField inputTextField, Label inputLabel, String validationText, String requiredLength) {
        boolean isCorrect = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[a-zA-Z0-9]{1,"+ requiredLength +"}")) {
        	isCorrect = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        System.out.println(inputTextField.getText().matches("[a-zA-Z0-9]{1,"+ requiredLength +"}"));
        return isCorrect;
	}
	
	public static boolean textNumber(TextField inputTextField, Label inputLabel, String validationText,String minimumLengte, String requiredLength) {
        boolean isCorrect = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[0-9]{"+ minimumLengte +","+ requiredLength +"}")) {
        	isCorrect = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        System.out.println(inputTextField.getText().matches("[0-9]{1,"+ requiredLength +"}"));
        return isCorrect;
	}
	
	public static boolean textFloat(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isCorrect = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[0-9]{1,4}.[0-9]{2}")) {
        	isCorrect = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        System.out.println(inputTextField.getText().matches("[0-9]{1,4}.[0-9]{2}"));
        return isCorrect;
	}
	
	public static boolean textPhone(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isCorrect = true;
        String validationString = null;

        if (!inputTextField.getText().matches("([+]{0,1}[0-9]{0,2}[\\s]{0,1}[0-9]{3}[-]{0,1}[0-9]{3}[-]{0,1}[0-9]{3})")) {
        	isCorrect = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        System.out.println(inputTextField.getText().matches("([+]{0,1})([0-9]{0,2}[\\s]{0,1})([0-9]{0,3}[-]{0,1})([0-9]{0,3}[-]{0,1})([0-9]{0,3})"));
        return isCorrect;
	}//[0-9]{1,4},[0-9]{2}
	
	public static boolean textPassword(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isCorrect = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[a-zA-Z0-9a-zA-Z_!$@#^&]{5,20}")) {
        	isCorrect = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        System.out.println(inputTextField.getText().matches("[a-zA-Z0-9a-zA-Z_!$@#^&]{5,20}"));
        return isCorrect;
	}
}
