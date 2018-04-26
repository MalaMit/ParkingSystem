package applicationController;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import databaseModel.ParkingSpot;
import databaseModel.TypeVehicle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CreateClientController implements Initializable {

	private UserPanelController userPanelController;
	
	@FXML
	private JFXComboBox<TypeVehicle> typeVehicleBoxID;
   
	@FXML
    private JFXComboBox<Integer> timeBoxID;
	
	@FXML
	private Label viewPriceLabelID;
    
	@FXML
    private Label viewSelectSpotLabelID;
	
    @FXML
    private Label chargeLabelID;

	@FXML
	private TableColumn<ParkingSpot, String> parkingPlaceColumn;

	@FXML
	private TableColumn<ParkingSpot, String> statusColumn;

	@FXML
	private TableView<ParkingSpot> pPlaceTable;
	
    @FXML
    private TextField firstNameTextID;

    @FXML
    private TextField licensePlateTextID;

    @FXML
    private TextField phoneNumberTextID;

    @FXML
    private TextField secondNameTextID;
    
    @FXML
    private JFXButton searchButtonID;
   
    @FXML
    private JFXButton calculatePriceButtonID;
    
    @FXML
    private JFXButton selectSpotButtonID;
    
    @FXML
    private JFXButton nextPageButtonID;
    
    @FXML
    private JFXCheckBox checkBoxCllientID;
    
    static Thread thred = new Thread();

	@FXML
	void nextPage(ActionEvent event) {
		//create client
		if(checkBoxCllientID.isSelected() == true && FirstPanelController.clientDAOImpl.checkLicensePlate(licensePlateTextID.getText()) == true) {
			FirstPanelController.parkingTimeDAOImpl.insertParkingTime(licensePlateTextID.getText(), dataAndTime(timeBoxID.getSelectionModel().getSelectedItem()), Integer.parseInt(chargeLabelID.getText()), typeVehicleBoxID.getValue().toString(), viewSelectSpotLabelID.getText());
			FirstPanelController.parkingSpotDAOImpl.changeStatusSpot(viewSelectSpotLabelID.getText());
			//end view
			userPanelController.endCreateClientPanel();
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("License Plate error!");
			alert.setHeaderText("Your license plate is invalid. Please try again");

			alert.showAndWait();
		}
		
		if(checkBoxCllientID.isSelected() == false) {
			FirstPanelController.clientDAOImpl.insertClient(licensePlateTextID.getText(), firstNameTextID.getText(), secondNameTextID.getText(), phoneNumberTextID.getText());	
			FirstPanelController.parkingTimeDAOImpl.insertParkingTime(licensePlateTextID.getText(), dataAndTime(timeBoxID.getSelectionModel().getSelectedItem()), Integer.parseInt(chargeLabelID.getText()), typeVehicleBoxID.getValue().toString(), viewSelectSpotLabelID.getText());
			FirstPanelController.parkingSpotDAOImpl.changeStatusSpot(viewSelectSpotLabelID.getText());
			//end view
			userPanelController.endCreateClientPanel();			
		}	
	}

	@FXML
	void searchButton(ActionEvent event) {
		viewSelectSpotLabelID.setText(""); //clean selectSpot Label
		
		viewPriceLabelID.setText(Integer.toString(FirstPanelController.typeVehicleDAOImpl.getPrice(typeVehicleBoxID.getValue().toString())));

		parkingPlaceColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberSpotProperty());
		parkingPlaceColumn.setStyle("-fx-alignment: CENTER;");
		statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
		statusColumn.setStyle("-fx-alignment: CENTER;");

		pPlaceTable.setItems(FirstPanelController.parkingSpotDAOImpl.getAllParkingSpot(typeVehicleBoxID.getValue().toString()));
	}
	
    @FXML
    void selectSpotButton(ActionEvent event) {	
    	if(pPlaceTable.getSelectionModel().getSelectedIndex() >= 0)
    	viewSelectSpotLabelID.setText(pPlaceTable.getSelectionModel().getSelectedItem().getNumberSpot());
    }

    @FXML
    void calculatePriceButton(ActionEvent event) {
    	chargeLabelID.setText(Integer.toString(Integer.parseInt(viewPriceLabelID.getText()) * timeBoxID.getSelectionModel().getSelectedItem()));
    }
    
    @FXML
    void checkBoxCllient(ActionEvent event) {
		if(checkBoxCllientID.isSelected() == false) {
			licensePlateTextID.disableProperty().bind(Bindings.isEmpty(phoneNumberTextID.textProperty()) .or(Bindings.isEmpty(viewSelectSpotLabelID.textProperty())));
		}else {
			licensePlateTextID.disableProperty().bind(Bindings.isEmpty(viewSelectSpotLabelID.textProperty()));
		}
    }
    
	public void initialize(URL arg0, ResourceBundle arg1) {
		timeBoxID.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 24);
		//// Metoda toString potem może zrobie konwerter
		ArrayList<TypeVehicle> typeV = FirstPanelController.typeVehicleDAOImpl.getAllTypeVehicle();
		typeVehicleBoxID.getItems().addAll(typeV);
		
		//bind property
		searchButtonID.disableProperty().bind(BooleanExpression.booleanExpression(this.typeVehicleBoxID.getSelectionModel().selectedItemProperty().isNull()));
		firstNameTextID.disableProperty().bind((BooleanProperty.booleanProperty(this.checkBoxCllientID.selectedProperty()) .or(Bindings.isEmpty(viewSelectSpotLabelID.textProperty()))));
		secondNameTextID.disableProperty().bind(Bindings.isEmpty(firstNameTextID.textProperty()) .or(Bindings.isEmpty(viewSelectSpotLabelID.textProperty()) .or(BooleanProperty.booleanProperty(this.checkBoxCllientID.selectedProperty()))));
		phoneNumberTextID.disableProperty().bind(Bindings.isEmpty(secondNameTextID.textProperty()).or(Bindings.isEmpty(viewSelectSpotLabelID.textProperty()) .or(BooleanProperty.booleanProperty(this.checkBoxCllientID.selectedProperty()))));	
		licensePlateTextID.disableProperty().bind(Bindings.isEmpty(phoneNumberTextID.textProperty()) .or(Bindings.isEmpty(viewSelectSpotLabelID.textProperty())));
		timeBoxID.disableProperty().bind(Bindings.isEmpty(licensePlateTextID.textProperty()).or(Bindings.isEmpty(viewSelectSpotLabelID.textProperty())));
		calculatePriceButtonID.disableProperty().bind(BooleanExpression.booleanExpression(this.timeBoxID.getSelectionModel().selectedItemProperty().isNull()) .or(Bindings.isEmpty(viewSelectSpotLabelID.textProperty())));
		selectSpotButtonID.disableProperty().bind(BooleanExpression.booleanExpression(this.pPlaceTable.getSelectionModel().selectedItemProperty().isNull()));
		nextPageButtonID.disableProperty().bind(Bindings.isEmpty(chargeLabelID.textProperty()) .or(Bindings.isEmpty(viewSelectSpotLabelID.textProperty())));

	}

	public void setUserPanelController(UserPanelController userPanelController) {
		this.userPanelController = userPanelController;
	}

	public static String dataAndTime(int addHour) {
		LocalDateTime localTime = LocalDateTime.now().plusHours(addHour);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = localTime.format(formatter);
		return formatDateTime;
	}
	
}
