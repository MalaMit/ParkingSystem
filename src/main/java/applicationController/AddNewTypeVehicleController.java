package applicationController;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import dataValidation.DataValidation;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class AddNewTypeVehicleController implements Initializable{
	
    @FXML
    private JFXTextField typeVehicleFieldID;

    @FXML
    private JFXTextField priceFieldID;

    @FXML
    private JFXButton cancelButtonID;

    @FXML
    private JFXButton applyButtonID;
    
    @FXML
    private Label priceLabelID;

    @FXML
    private Label typeVehicleLabelID;

    @FXML
    void applyButton(ActionEvent event) {
    	boolean typeVeh = DataValidation.textAlphabetWithPolishMarks(typeVehicleFieldID, typeVehicleLabelID, "Type Vehicle is Required (MAX 30 characters)", "30");
    	boolean price = DataValidation.textNumber(priceFieldID, priceLabelID, "Price is Required (MAX 3 number)", "1", "3");
	    
    	if(typeVeh && price) {
    		if(!CreateClientController.typeVehicleDAOImpl.checkTypeVehicleIsExist(typeVehicleFieldID.getText())) {
    				CreateClientController.typeVehicleDAOImpl.insertTypeVehicle(typeVehicleFieldID.getText(),Integer.parseInt(priceFieldID.getText()));
    				((Node)(event.getSource())).getScene().getWindow().hide();
    		}
    	}
    }

    @FXML
    void cancelButton(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		priceFieldID.disableProperty().bind(Bindings.isEmpty(this.typeVehicleFieldID.textProperty()));
		applyButtonID.disableProperty().bind(Bindings.isEmpty(this.priceFieldID.textProperty()));
		
	}

}