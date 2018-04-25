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
    private Label parkingSpotLabel;

    @FXML
    private Label declareTimeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label typeVehicleLabel;
    
    @FXML
    private Label TotalPriceLabel;

    @FXML
    private Label OverrunTimeLabel;
    
    @FXML
    private JFXButton SearchClientForLicenseButtonID;

    @FXML
    private JFXButton PayButtonID;
    
    @FXML
    private JFXTextField LicensePlateField;
    
    @FXML
    void PayButton(ActionEvent event) {
    	FirstPanelController.parkingSpotDAOImpl.changeStatusSpotExit(parkingSpotLabel.getText());
    	backToMenu();
    }

    @FXML
    void SearchClientForLicenseButton(ActionEvent event) {
    	if(FirstPanelController.parkingTimeDAOImpl.checkLicensePlateExist(LicensePlateField.getText()) == true) {
    		ObservableList<ParkingTime> lista = FirstPanelController.parkingTimeDAOImpl.getToExitParking(LicensePlateField.getText());
    		
    		declareTimeLabel.setText(lista.get(0).getTimeOut().substring(0, (lista.get(0).getTimeOut()).indexOf(".")));
    		priceLabel.setText(Float.toString(lista.get(0).getBill()));
    		parkingSpotLabel.setText(lista.get(0).getParkingNumber());
    		typeVehicleLabel.setText(lista.get(0).getTypeVehicle());	
    		
    		OverrunTimeLabel.setText(Integer.toString(FirstPanelController.parkingTimeDAOImpl.getOverrunTimePrice(LicensePlateField.getText())));
    		
    		TotalPriceLabel.setText(Float.toString((lista.get(0).getBill()) + ((FirstPanelController.parkingTimeDAOImpl.getOverrunTimePrice(LicensePlateField.getText()))) * (FirstPanelController.typeVehicleDAOImpl.getPrice(typeVehicleLabel.getText()))));
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
		SearchClientForLicenseButtonID.disableProperty().bind(Bindings.isEmpty(LicensePlateField.textProperty()));
		PayButtonID.disableProperty().bind(Bindings.isEmpty(TotalPriceLabel.textProperty()));
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
