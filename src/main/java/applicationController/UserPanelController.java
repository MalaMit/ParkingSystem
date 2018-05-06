package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import applicationStart.Main;
import databaseDAOImpl.ClientDAOImpl;
import databaseDAOImpl.ParkingSpotDAOImpl;
import databaseDAOImpl.ParkingTimeDAOImpl;
import databaseDAOImpl.TypeVehicleDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UserPanelController implements Initializable {
	
	static TypeVehicleDAOImpl typeVehicleDAOImpl = new TypeVehicleDAOImpl();
	static ParkingSpotDAOImpl parkingSpotDAOImpl = new ParkingSpotDAOImpl();
	static ClientDAOImpl clientDAOImpl = new ClientDAOImpl();
	static ParkingTimeDAOImpl parkingTimeDAOImpl = new ParkingTimeDAOImpl();

	@FXML
	private StackPane userMainStakPane;

	@FXML
	void outProgramButton(ActionEvent event) {
		backToMain();
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		createClientControllerPanel();
	}

	public void createClientControllerPanel() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/CreateClient.fxml"));
		StackPane stackPane = null;
		try {
			stackPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CreateClientController createClientController = loader.getController();
		createClientController.setUserPanelController(this);
		setScreen(stackPane);
	}

	public void endCreateClientPanel() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/EndCreateClient.fxml"));
		StackPane stackPane = null;
		try {
			stackPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EndCreateClientController endCreateClientController = loader.getController();
		endCreateClientController.setUserPanelController(this);
		setScreen(stackPane);
	}
	
	public void backToMain() {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/FirstPanel.fxml"));
			Scene scene = new Scene(parent);

			Stage window = Main.getPrimaryStage();

			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setScreen(StackPane stackPane) {
		userMainStakPane.getChildren().clear();
		userMainStakPane.getChildren().add(stackPane);
	}

}
