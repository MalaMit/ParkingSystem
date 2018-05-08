package applicationController;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import dataValidation.DataValidation;
import databaseModel.EnumEmployeeType;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

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
    private Label nameLabelID;

    @FXML
    private Label surnameLabelID;

    @FXML
    private Label peselLabelID;

    @FXML
    private Label phoneLabelID;

    @FXML
    private Label hourlyWageLabelID;

    @FXML
    void addNewEmployeeButton(ActionEvent event) {
    	boolean name = DataValidation.textAlphabetWithPolishMarks(nameFieldID, nameLabelID, "Name is Required (MAX 20 characters)", "20");
    	boolean surname = DataValidation.textAlphabetWithPolishMarks(surnameFieldID, surnameLabelID, "Surname is Required (MAX 20 characters)", "20");
    	boolean pesel = DataValidation.textNumber(peselFieldID, peselLabelID, "Pesel is Required (MAX 20 number)", "10", "20");
    	boolean phone = DataValidation.textPhone(phoneFieldID, phoneLabelID, "Phone is Required");
    	boolean hourlyWage = DataValidation.textFloat(hourlyWageFieldID, hourlyWageLabelID, "Name is Required (xxxx.xx)");
    	
    	if(name && surname && pesel && phone && hourlyWage) {
			EmployeePanelController.employeeDAOImpl.insertEmployee(Long.parseLong(peselFieldID.getText()),
					nameFieldID.getText(), surnameFieldID.getText(), String.valueOf(dateEmployedID.getValue()),
					Float.parseFloat(hourlyWageFieldID.getText()), phoneFieldID.getText(),
					String.valueOf(positionBoxID.getSelectionModel().getSelectedItem()));
			
			((Node)(event.getSource())).getScene().getWindow().hide();
    	}
    }
    
    @FXML
    void cancelButton(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<EnumEmployeeType> positionList = FXCollections.observableArrayList(EnumEmployeeType.values());
		positionBoxID.setItems(positionList);
		//dorobic css
		positionBoxID.setStyle("-fx-text-fill: White; -fx-font-size: 20;");
		
		surnameFieldID.disableProperty().bind(Bindings.isEmpty(nameFieldID.textProperty()));
		peselFieldID.disableProperty().bind(Bindings.isEmpty(surnameFieldID.textProperty()));
		phoneFieldID.disableProperty().bind(Bindings.isEmpty(peselFieldID.textProperty()));
		hourlyWageFieldID.disableProperty().bind(Bindings.isEmpty(phoneFieldID.textProperty()));
		positionBoxID.disableProperty().bind(Bindings.isEmpty(hourlyWageFieldID.textProperty()));
		dateEmployedID.disableProperty().bind(BooleanExpression.booleanExpression(this.positionBoxID.getSelectionModel().selectedItemProperty().isNull()));
		addNewEmployeeButtonID.disableProperty().bind(Bindings.isNull(dateEmployedID.valueProperty()));
	}

	
}