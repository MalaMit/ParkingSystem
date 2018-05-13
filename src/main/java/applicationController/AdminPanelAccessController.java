package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import databaseDAOImpl.AdminDAOImpl;
import databaseDAOImpl.ParkingHistoryDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminPanelAccessController implements Initializable {
	
	static AdminDAOImpl adminDAOImpl = new AdminDAOImpl();
	static ParkingHistoryDAOImpl parkingHistoryDAOImpl = new ParkingHistoryDAOImpl();

	@FXML
	private StackPane adminMainStackPane;

	@FXML
	void adminAccountsAction(ActionEvent event) {
		adminAccountPanel();
	}

	@FXML
	void clientsAction(ActionEvent event) {
		clientsViewForAdminPanel();

	}

	@FXML
	void employeesAction(ActionEvent event) {
		employeePanel();
	}

	@FXML
	void parkingSpotsAction(ActionEvent event) {
		parkingSpotsViewAdmin();
	}

	@FXML
	void logOutAdmin(ActionEvent event) {
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void adminAccountPanel() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AdminAccountPanel.fxml"));
		StackPane stackPane = null;
		try {
			stackPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AdminAccountPanelController adminAccountPanelController = loader.getController();
		adminAccountPanelController.setAdminAccountPanel(this);
		setScreen(stackPane);
	}
	
	public void clientsViewForAdminPanel() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ClientsViewForAdmin.fxml"));
		StackPane stackPane = null;
		try {
			stackPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClientsViewForAdminController clientsViewForAdminController = loader.getController();
		clientsViewForAdminController.setClientsViewForAdmin(this);
		setScreen(stackPane);
	}
	
	public void parkingSpotsViewAdmin() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ParkingTimeViewAdmin.fxml"));
		StackPane stackPane = null;
		try {
			stackPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ParkingTimeViewAdminController parkingTimeViewAdminController = loader.getController();
		parkingTimeViewAdminController.setParkingSpotsViewAdmin(this);
		setScreen(stackPane);
	}
	
	public void employeePanel() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/EmployeePanel.fxml"));
		StackPane stackPane = null;
		try {
			stackPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EmployeePanelController employeePanelController = loader.getController();
		employeePanelController.setEmployeePanel(this);
		setScreen(stackPane);
	}

	public void setScreen(StackPane stackPane) {
		adminMainStackPane.getChildren().clear();
		adminMainStackPane.getChildren().add(stackPane);
	}
	
}
