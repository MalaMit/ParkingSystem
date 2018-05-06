package applicationController;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import databaseModel.EnumEmployeeType;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

public class AddNewEmployeeController implements Initializable{
	
	@FXML
    private JFXTextField nameFieldID;

    @FXML
    private JFXTextField surnameFieldID;

    @FXML
    private JFXTextField peselFieldID;

    @FXML
    private JFXTextField phoneFieldID;

    @FXML
    private JFXTextField hourlyWageFieldID;

    @FXML
    private JFXComboBox<EnumEmployeeType> positionBoxID;

    @FXML
    private JFXDatePicker dateEmployedID;

    @FXML
    private JFXButton addNewEmployeeButtonID;

    @FXML
    private JFXButton cancelButtonID;

    @FXML
    void addNewEmployeeButton(ActionEvent event) {
		EmployeePanelController.employeeDAOImpl.insertEmployee(Long.parseLong(peselFieldID.getText()),
				nameFieldID.getText(), surnameFieldID.getText(), String.valueOf(dateEmployedID.getValue()),
				Float.parseFloat(hourlyWageFieldID.getText()), phoneFieldID.getText(),
				String.valueOf(positionBoxID.getSelectionModel().getSelectedItem()));
		
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    @FXML
    void cancelButton(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<EnumEmployeeType> positionList = FXCollections.observableArrayList(EnumEmployeeType.values());
		positionBoxID.setItems(positionList);
		
		surnameFieldID.disableProperty().bind(Bindings.isEmpty(nameFieldID.textProperty()));
		peselFieldID.disableProperty().bind(Bindings.isEmpty(surnameFieldID.textProperty()));
		phoneFieldID.disableProperty().bind(Bindings.isEmpty(peselFieldID.textProperty()));
		hourlyWageFieldID.disableProperty().bind(Bindings.isEmpty(phoneFieldID.textProperty()));
		positionBoxID.disableProperty().bind(Bindings.isEmpty(hourlyWageFieldID.textProperty()));
		dateEmployedID.disableProperty().bind(BooleanExpression.booleanExpression(this.positionBoxID.getSelectionModel().selectedItemProperty().isNull()));
		addNewEmployeeButtonID.disableProperty().bind(Bindings.isNull(dateEmployedID.valueProperty()));
	}

	
}