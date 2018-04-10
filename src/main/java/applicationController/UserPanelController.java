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
	void personalDateButton(ActionEvent event) {
		personalDatePanel();
	}

	@FXML
	void carAndSpaceButton(ActionEvent event) {
		carTypeAndSpacePanel();
	}

	@FXML
	void timeButton(ActionEvent event) {
		setTimeParkingPanel();
	}
	
	@FXML
	void duePaymentButton(ActionEvent event) {
		duePaymentPanel();
	}

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
		personalDatePanel();
	}
	
	public void personalDatePanel() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/PersonalDatePanel.fxml"));
		StackPane stackPane = null;
		try {
			stackPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PersonalDatePanelController personalDatePanelController = loader.getController();
		personalDatePanelController.setUserPanelController(this);
		setScreen(stackPane);
	}
	
	public void carTypeAndSpacePanel() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/CarTypeAndSpace.fxml"));
		StackPane stackPane = null;
		try {
			stackPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CarTypeAndSpaceController carTypeAndSpaceController = loader.getController();
		carTypeAndSpaceController.setUserPanelController(this);
		setScreen(stackPane);
	}
	
	public void setTimeParkingPanel() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/SetTimeParking.fxml"));
		StackPane stackPane = null;
		try {
			stackPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SetTimeParkingController setTimeParkingController = loader.getController();
		setTimeParkingController.setUserPanelController(this);
		setScreen(stackPane);
	}
	
	public void duePaymentPanel() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/DuePaymentPanel.fxml"));
		StackPane stackPane = null;	
		try {
			stackPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DuePaymentPanelController duePaymentPanelController = loader.getController();
		duePaymentPanelController.setUserPanelController(this);
		setScreen(stackPane);
	}

	public void setScreen(StackPane stackPane) {
		userMainStakPane.getChildren().clear();
		userMainStakPane.getChildren().add(stackPane);
	}

}
