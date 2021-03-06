package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import applicationStart.Main;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLoginPanelController implements Initializable {
	
	private static final String  accessAdmin = "/fxml/AdminPanelAccess.fxml";
	private static final String backToChoose = "/fxml/ChooseAdminOrEmployeePanel.fxml";

	@FXML
	private TextField textLogin;

	@FXML
	private PasswordField textPassword;

	@FXML
	private JFXButton accessAdminButton;

	@FXML
	void accessAdminButtonAction(ActionEvent event) {

		if (AdminPanelAccessController.adminDAOImpl.getLoginAdminByLoginAndPassword(textLogin.getText(),
				textPassword.getText())) {
			loadFxml(accessAdmin);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Login error!");
			alert.setHeaderText("Your login or password is invalid. Please try again");

			alert.showAndWait();
		}
	}

	@FXML
	public void backToChooseEmplOrAdmButton(ActionEvent event) {
		loadFxml(backToChoose);
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// Bind property
		textPassword.disableProperty().bind(Bindings.isEmpty(textLogin.textProperty()));
		
		accessAdminButton.disableProperty()
				.bind(Bindings.isEmpty(textLogin.textProperty()).or(Bindings.isEmpty(textPassword.textProperty())));
	}
	
	private void loadFxml(String pathFxml) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource(pathFxml));
			Scene scene = new Scene(parent);
			
			Stage window = Main.getPrimaryStage();

			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}