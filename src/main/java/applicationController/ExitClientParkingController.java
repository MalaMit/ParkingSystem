package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import applicationStart.Main;
import databaseModel.ParkingTime;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ExitClientParkingController implements Initializable{

    @FXML
    private Label parkingSpotLabelID;

    @FXML
    private Label declareTimeLabelID;

    @FXML
    private Label timeInLabelID;

    @FXML
    private Label priceLabelID;

    @FXML
    private Label typeVehicleLabelID;
    
    @FXML
    private Label totalPriceLabelID;

    @FXML
    private Label overrunTimeLabelID;
    
    @FXML
    private JFXButton searchClientForLicenseButtonID;

    @FXML
    private JFXButton payButtonID;
    
    @FXML
    private JFXTextField licensePlateFieldID;
    
    @FXML
    void PayButton(ActionEvent event) {
    	FirstPanelController.parkingSpotDAOImpl.changeStatusSpotExit(parkingSpotLabelID.getText());
    	FirstPanelController.parkingTimeDAOImpl.deleteParkingTime(licensePlateFieldID.getText());
    	FirstPanelController.parkingHistoryDAOImpl.insertParkingHistory(timeInLabelID.getText(), declareTimeLabelID.getText(), totalPriceLabelID.getText(), licensePlateFieldID.getText(), parkingSpotLabelID.getText());
    	backToMenu();
    }

    @FXML
    void SearchClientForLicenseButton(ActionEvent event) {
    	if(FirstPanelController.parkingTimeDAOImpl.checkLicensePlateExist(licensePlateFieldID.getText()) == true) {
    		ObservableList<ParkingTime> lista = FirstPanelController.parkingTimeDAOImpl.getToExitParking(licensePlateFieldID.getText());
    		
    		timeInLabelID.setText(lista.get(0).getTimeIn().substring(0 , (lista.get(0).getTimeIn().indexOf("."))));
    		declareTimeLabelID.setText(lista.get(0).getTimeOut().substring(0, (lista.get(0).getTimeOut()).indexOf(".")));
    		priceLabelID.setText(Float.toString(lista.get(0).getBill()));
    		parkingSpotLabelID.setText(lista.get(0).getParkingNumber());
    		typeVehicleLabelID.setText(lista.get(0).getTypeVehicle());	
    		
    		overrunTimeLabelID.setText(Integer.toString(FirstPanelController.parkingTimeDAOImpl.getOverrunTimePrice(licensePlateFieldID.getText())));
    		
    		totalPriceLabelID.setText(Float.toString((lista.get(0).getBill()) + ((FirstPanelController.parkingTimeDAOImpl.getOverrunTimePrice(licensePlateFieldID.getText()))) * (FirstPanelController.typeVehicleDAOImpl.getPrice(typeVehicleLabelID.getText()))));
    	}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("License Plate error!");
			alert.setHeaderText("Your license plate is invalid. Please try again");

			alert.showAndWait();
    	}

    }
	
    @FXML
    void outProgramButton(ActionEvent event) {
		backToMenu();
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		searchClientForLicenseButtonID.disableProperty().bind(Bindings.isEmpty(licensePlateFieldID.textProperty()));
		payButtonID.disableProperty().bind(Bindings.isEmpty(totalPriceLabelID.textProperty()));
	}
	
	public void backToMenu() {
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

}
