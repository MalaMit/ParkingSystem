package applicationController;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AdminChangePasswordController implements Initializable {

	@FXML
	private JFXPasswordField passwordOneID;

	@FXML
	private JFXPasswordField passwordTwoID;

	@FXML
	private JFXTextField adminAccountID;

	@FXML
	private JFXButton applyButtonID;

	@FXML
	private JFXButton cancelButtonID;

	@FXML
	void applyButton(ActionEvent event) {
		if (passwordOneID.getText().equals(passwordTwoID.getText())) {
			FirstPanelController.adminDAOImpl.updateAdminChangePassword(Integer.parseInt(adminAccountID.getText()),
					passwordOneID.getText());
			((Node)(event.getSource())).getScene().getWindow().hide();
		}	
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Password or Login error!");
			alert.setHeaderText("Your imput passwords are not the same or login existing. Please try again");

			alert.showAndWait();
		}
	}

	@FXML
	void cancelButton(ActionEvent event) {
		 ((Node)(event.getSource())).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		passwordTwoID.disableProperty().bind(Bindings.isEmpty(passwordOneID.textProperty()));
		applyButtonID.disableProperty().bind(Bindings.isEmpty(passwordTwoID.textProperty()));

	}

	public void setAccountID(String adminID) {
		adminAccountID.setText(adminID);
	}

}
