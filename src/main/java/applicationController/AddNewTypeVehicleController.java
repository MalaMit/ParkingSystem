package applicationController;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

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
    void applyButton(ActionEvent event) {
    	if(UserPanelController.typeVehicleDAOImpl.checkTypeVehicleIsExist(typeVehicleFieldID.getText()) != true) {
    	UserPanelController.typeVehicleDAOImpl.insertTypeVehicle(typeVehicleFieldID.getText(),Integer.parseInt(priceFieldID.getText()));
    	((Node)(event.getSource())).getScene().getWindow().hide();
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