package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import applicationStart.Main;
import databaseDAOImpl.AdminDAOImpl;
import databaseDAOImpl.ClientDAOImpl;
import databaseDAOImpl.ParkingHistoryDAOImpl;
import databaseDAOImpl.ParkingSpotDAOImpl;
import databaseDAOImpl.ParkingTimeDAOImpl;
import databaseDAOImpl.TypeVehicleDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FirstPanelController implements Initializable {

	static TypeVehicleDAOImpl typeVehicleDAOImpl = new TypeVehicleDAOImpl();
	static ParkingSpotDAOImpl parkingSpotDAOImpl = new ParkingSpotDAOImpl();
	static ClientDAOImpl clientDAOImpl = new ClientDAOImpl();
	static ParkingTimeDAOImpl parkingTimeDAOImpl = new ParkingTimeDAOImpl();
	static AdminDAOImpl adminDAOImpl = new AdminDAOImpl();
	static ParkingHistoryDAOImpl parkingHistoryDAOImpl = new ParkingHistoryDAOImpl();

	@FXML
	private void userPanelButton(ActionEvent event) {
		Parent parent1 = null;
		try {
			parent1 = FXMLLoader.load(getClass().getResource("/fxml/UserPanel.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(parent1);

		Stage window = Main.getPrimaryStage();

		window.setScene(scene);
		window.show();
	}

	@FXML
	private void adminLoginButton(ActionEvent event) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/AdminLoginPanel.fxml"));
			Scene scene = new Scene(parent);

			Stage window = Main.getPrimaryStage();

			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void exitParking(ActionEvent event) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/ExitClientParking.fxml"));
			Scene scene = new Scene(parent);

			Stage window = Main.getPrimaryStage();

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
