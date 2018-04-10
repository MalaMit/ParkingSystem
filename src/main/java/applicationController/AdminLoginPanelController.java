package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import databaseDAOImpl.AdminDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLoginPanelController implements Initializable {

	@FXML
	private TextField textLogin;

	@FXML
	private PasswordField textPassword;

	private AdminDAOImpl loginAdm = new AdminDAOImpl();

	@FXML
	void accessAdminButton(ActionEvent event) {

		if ((loginAdm.getAdminByLoginAndPassword(textLogin.getText(), textPassword.getText())) == true) {
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("/fxml/AdminPanelAccess.fxml"));
				Scene scene = new Scene(parent);

				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

				window.setScene(scene);
				window.show();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Invalid username or password!");
		}
	}

	@FXML
	void backToMainButton(ActionEvent event) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/FirstPanel.fxml"));
			Scene scene = new Scene(parent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}