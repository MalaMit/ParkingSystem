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

public class TypeVehicleChangePriceController implements Initializable {

    @FXML
    private JFXTextField typeVehicleFieldID;

    @FXML
    private JFXTextField newPriceFieldID;

    @FXML
    private JFXButton cancelButtonID;

    @FXML
    private JFXButton applyButtonID;

    @FXML
    void applyButton(ActionEvent event) {
    	CreateClientController.typeVehicleDAOImpl.updatePriceTypeVehicle(typeVehicleFieldID.getText(), Integer.parseInt(newPriceFieldID.getText()));
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void cancelButton(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		applyButtonID.disableProperty().bind(Bindings.isEmpty(this.newPriceFieldID.textProperty()));
		
	}

	public void setTypeVehicle(String type) {
		typeVehicleFieldID.setText(type);
		
	}

}