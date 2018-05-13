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

public class ChooseAdmOrEmplPanelController implements Initializable{
	
	private static final String adminLoginPanel = "/fxml/AdminLoginPanel.fxml";
	private static final String backToStart = "/fxml/FirstPanel.fxml";
	private static final String employeeTimeWork = "/fxml/EmployeeStartEndShift.fxml";

    @FXML
    void adminLoginPanelButton(ActionEvent event) {
		loadFxml(adminLoginPanel);
    }

    @FXML
    void backToMainButton(ActionEvent event) {
    	loadFxml(backToStart);
    }

    @FXML
    void employeeTimeWorkButton(ActionEvent event) {
    	loadFxml(employeeTimeWork);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
