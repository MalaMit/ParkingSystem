package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import applicationStart.Main;
import dataValidation.DataValidation;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EmployeeStartEndShiftController implements Initializable {
	
	private static final String backToChoose = "/fxml/ChooseAdminOrEmployeePanel.fxml";
	private static final String backToStart = "/fxml/FirstPanel.fxml";
	
    @FXML
    private JFXTextField peselFieldID;

    @FXML
    private JFXButton startWorkButtonID;

    @FXML
    private JFXButton endWorkButtonID; 

    @FXML
    private Label peselLabelID;

    @FXML
    void backToChooseEmplOrAdmButton(ActionEvent event) {
    	loadFxml(backToChoose);
    }

    @FXML
    void endWorkButton(ActionEvent event) {
    	boolean pesel = DataValidation.textNumber(peselFieldID, peselLabelID, "Pesel is Required", "10", "20");
    	
    	if(pesel) {
	    	if(EmployeePanelController.employeeDAOImpl.checkPesel(Long.valueOf(peselFieldID.getText()))) {
	    		EmployeeShiftWorkController.shiftDAOImpl.insertEndWorkTime(Long.valueOf(peselFieldID.getText()));
	    		loadFxml(backToStart);
	    	}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Pesel error!");
				alert.setHeaderText("Your pesel is no existed. Please try again");
	
				alert.showAndWait();
	    	}
    	}
    }

    @FXML
    void startWorkButton(ActionEvent event) {
    	boolean pesel = DataValidation.textNumber(peselFieldID, peselLabelID, "Pesel is Required", "10", "20");
    	
    	if(pesel) {
	    	if(EmployeePanelController.employeeDAOImpl.checkPesel(Long.valueOf(peselFieldID.getText()))) {
	    		EmployeeShiftWorkController.shiftDAOImpl.insertStartWorkTime(Long.valueOf(peselFieldID.getText()));
	    		loadFxml(backToStart);
	    	}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Pesel error!");
				alert.setHeaderText("Your pesel is no existed. Please try again");
	
				alert.showAndWait();
			}
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//bind
		startWorkButtonID.disableProperty().bind(Bindings.isEmpty(peselFieldID.textProperty()));
		endWorkButtonID.disableProperty().bind(Bindings.isEmpty(peselFieldID.textProperty()));
		
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
