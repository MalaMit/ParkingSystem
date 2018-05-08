package applicationController;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import dataValidation.DataValidation;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class AddNewAdminAccountController implements Initializable{	

    @FXML
    private JFXTextField loginID;

    @FXML
    private JFXPasswordField passwordOneID;

    @FXML
    private JFXPasswordField passwordTwoID;

    @FXML
    private JFXTextField firstNameID;

    @FXML
    private JFXTextField secondNameID;

    @FXML
    private JFXButton applyButtonID;

    @FXML
    private JFXButton cancelButtonID;

    @FXML
    private Label loginLabelID;

    @FXML
    private Label passwordOneLabelID;

    @FXML
    private Label passwordTwoLabelID;

    @FXML
    private Label firstNameLabelID;

    @FXML
    private Label secondNameLabelID;

    @FXML
    void applyButton(ActionEvent event) {
    	boolean login = DataValidation.textAlphabetAndNumber(loginID, loginLabelID, "Login is Required (1-20 characters)", "20");
    	boolean passwordOne = DataValidation.textPassword(passwordOneID, passwordOneLabelID, "Password is Required (5-20 characters)");
    	boolean passwordTwo = DataValidation.textPassword(passwordTwoID, passwordTwoLabelID, "Password is Required (5-20 characters)");
    	boolean fName = DataValidation.textAlphabetWithPolishMarks(firstNameID, firstNameLabelID, "First name is Required (MAX 20 characters)", "20");
    	boolean sName = DataValidation.textAlphabetWithPolishMarks(secondNameID, secondNameLabelID, "Second Name is Required (MAX 20 characters)", "20");
    	
    	if(login && passwordOne && passwordTwo && fName && sName) {
			if (passwordOneID.getText().equals(passwordTwoID.getText())
					&& !(AdminPanelAccessController.adminDAOImpl.checkLoginIsExisting(loginID.getText()))) {

				AdminPanelAccessController.adminDAOImpl.insertAdmin(loginID.getText(), passwordOneID.getText(),
						firstNameID.getText(), secondNameID.getText());
				((Node) (event.getSource())).getScene().getWindow().hide();

			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Password error!");
				alert.setHeaderText("Your imput passwords are not the same or login existing. Please try again");

				alert.showAndWait();

			}
    	}
    }

    @FXML
    void cancelButton(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		passwordOneID.disableProperty().bind(Bindings.isEmpty(loginID.textProperty()));
		passwordTwoID.disableProperty().bind(Bindings.isEmpty(passwordOneID.textProperty()));
		firstNameID.disableProperty().bind(Bindings.isEmpty(passwordTwoID.textProperty()));
		secondNameID.disableProperty().bind(Bindings.isEmpty(firstNameID.textProperty()));
		applyButtonID.disableProperty().bind(Bindings.isEmpty(secondNameID.textProperty()));
		
		
	}

}
