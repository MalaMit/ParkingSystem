package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import applicationStart.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FirstPanelController implements Initializable {

	private static final String userPanel = "/fxml/CreateClient.fxml";
	private static final String login = "/fxml/ChooseAdminOrEmployeePanel.fxml";
	private static final String exit = "/fxml/ExitClientParking.fxml";
	
	@FXML
	private void userPanelButton(ActionEvent event) {
		loadFxml(userPanel);
	}

	@FXML
	private void loginButton(ActionEvent event) {
		loadFxml(login);
	}

	@FXML
	void exitParking(ActionEvent event) {
		loadFxml(exit);
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

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
