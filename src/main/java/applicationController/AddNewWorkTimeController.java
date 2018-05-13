package applicationController;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import databaseModel.EnumShiftType;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

public class AddNewWorkTimeController implements Initializable{
	
		@FXML
	    private JFXTextField peselFieldID;

	    @FXML
	    private JFXComboBox<EnumShiftType> workTypeBoxID;

	    @FXML
	    private JFXDatePicker dateStartID;

	    @FXML
	    private JFXDatePicker dateEndID;

	    @FXML
	    private JFXTimePicker timeStartID;

	    @FXML
	    private JFXTimePicker timeEndID;

	    @FXML
	    private JFXButton cancelButtonID;

	    @FXML
	    private JFXButton applyButtonID;

	    @FXML
	    void applyButton(ActionEvent event) {
	    	StringBuilder sbStart = new StringBuilder().append(dateStartID.getValue().toString()).append(" ").append(timeStartID.getValue().toString());
	    	StringBuilder sbEnd = new StringBuilder().append(dateEndID.getValue().toString()).append(" ").append(timeEndID.getValue().toString());
	    	EmployeeShiftWorkController.shiftDAOImpl.insertNewWorkTime(sbStart.toString(), sbEnd.toString(), Long.parseLong(peselFieldID.getText()), workTypeBoxID.getValue().toString());
	    	((Node) (event.getSource())).getScene().getWindow().hide();
	    }

	    @FXML
	    void cancelButton(ActionEvent event) {
	    	((Node) (event.getSource())).getScene().getWindow().hide();
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<EnumShiftType> workTypeList = FXCollections.observableArrayList(EnumShiftType.values());
		workTypeBoxID.setItems(workTypeList);
		
		timeStartID.disableProperty().bind(BooleanExpression.booleanExpression(this.workTypeBoxID.getSelectionModel().selectedItemProperty().isNull()));
		dateStartID.disableProperty().bind(Bindings.isNull(timeStartID.valueProperty()));
		timeEndID.disableProperty().bind(Bindings.isNull(dateStartID.valueProperty()));
		dateEndID.disableProperty().bind(Bindings.isNull(timeEndID.valueProperty()));
		applyButtonID.disableProperty().bind(Bindings.isNull(dateEndID.valueProperty()));
		
		//style
		workTypeBoxID.setStyle("-fx-background-color: White; -fx-font-size: 20;");
		dateStartID.setStyle("-fx-background-color: White; -fx-font-size: 20;");
		dateEndID.setStyle("-fx-background-color: White; -fx-font-size: 20;");
		timeStartID.setStyle("-fx-background-color: White; -fx-font-size: 20;");
		timeEndID.setStyle("-fx-background-color: White; -fx-font-size: 20;");
	}
	
	public void setPesel(String pesel) {
		peselFieldID.setText(pesel);
	}

}