package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EmployeePanelController implements Initializable{
	
	@FXML
	private StackPane adminMainStackPane;
	@FXML
	private TableView employeeTableView;
	
	@FXML
	void adminAccountsAction(ActionEvent event) {

	}

	@FXML
	void clientsAction(ActionEvent event) {

	}

	@FXML
	void employeesAction(ActionEvent event) {
		

	}

	@FXML
	void parkingSpotsAction(ActionEvent event) {

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
	

	}

}
