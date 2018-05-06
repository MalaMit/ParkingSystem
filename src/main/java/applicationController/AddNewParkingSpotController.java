package applicationController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import databaseModel.TypeVehicle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;


public class AddNewParkingSpotController implements Initializable {
	

    @FXML
    private JFXTextField numberFieldID;

    @FXML
    private JFXComboBox<TypeVehicle> typeVehicleBoxID;

    @FXML
    private JFXButton cancelButtonID;

    @FXML
    private JFXButton applyButtonID;

    @FXML
    void applyButton(ActionEvent event) {
    	if((UserPanelController.parkingSpotDAOImpl.checkNumberFree(numberFieldID.getText())) != true) {
    		UserPanelController.parkingSpotDAOImpl.insertParkingSpot(numberFieldID.getText(), typeVehicleBoxID.getSelectionModel().getSelectedItem().getType());
	    	((Node)(event.getSource())).getScene().getWindow().hide();
    	}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Number error!");
			alert.setHeaderText("Number is invalid. Please try again");

			alert.showAndWait();
    	}
    }

    @FXML
    void cancelButton(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<TypeVehicle> typeV = UserPanelController.typeVehicleDAOImpl.getAllTypeVehicle();
		
		typeVehicleBoxID.setConverter(new StringConverter<TypeVehicle>() {
		    @Override
		    public String toString(TypeVehicle object) {
		        return object.getType();
		    }

		    @Override
		    public TypeVehicle fromString(String string) {
		        return null;
		    }

		});
		typeVehicleBoxID.getItems().addAll(typeV);
		
		typeVehicleBoxID.disableProperty().bind(Bindings.isEmpty(numberFieldID.textProperty()));
		typeVehicleBoxID.setStyle("-fx-background-color: White; -fx-font-size: 16;");
		applyButtonID.disableProperty().bind(BooleanExpression.booleanExpression(this.typeVehicleBoxID.getSelectionModel().selectedItemProperty().isNull()));
		
	}

}
