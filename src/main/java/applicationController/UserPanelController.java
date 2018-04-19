package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UserPanelController implements Initializable {

	@FXML
	private StackPane userMainStakPane;

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
		carTypeAndSpacePanel();
	}
	
	public void carTypeAndSpacePanel() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/CreateClient.fxml"));
		StackPane stackPane = null;
		try {
			stackPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CreateClientController carTypeAndSpaceController = loader.getController();
		carTypeAndSpaceController.setUserPanelController(this);
		setScreen(stackPane);
	}
	
	public void setScreen(StackPane stackPane) {
		userMainStakPane.getChildren().clear();
		userMainStakPane.getChildren().add(stackPane);
	}
	
}
