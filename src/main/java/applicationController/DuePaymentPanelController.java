package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DuePaymentPanelController implements Initializable {
	
	private UserPanelController userPanelController;
	
	@FXML
	void outProgramButton(ActionEvent event) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/FirstPanel.fxml"));
			Scene scene = new Scene(parent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	public void setUserPanelController(UserPanelController userPanelController) {
		this.userPanelController = userPanelController;
	}

}
