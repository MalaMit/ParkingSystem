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
	private JFXComboBox<TypeVehicle> typeVehicleBox;
   
	@FXML
    private JFXComboBox<Integer> timeBox;
	
	@FXML
	private Label viewPriceLabel;
    
	@FXML
    private Label viewSelectSpot;
	
    @FXML
    private Label chargeLabel;

	@FXML
	private TableColumn<ParkingSpot, String> parkingPlaceColumn;

	@FXML
	private TableColumn<ParkingSpot, String> statusColumn;

	@FXML
	private TableView<ParkingSpot> pPlaceTable;
	
    @FXML
    private TextField firstNameText;

    @FXML
    private TextField licensePlateText;

    @FXML
    private TextField phoneNumberText;

    @FXML
    private TextField secondNameText;
    
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
		if(checkBoxCllientID.isSelected() == true && FirstPanelController.clientDAOImpl.checkLicensePlate(licensePlateText.getText()) == true) {
			FirstPanelController.parkingTimeDAOImpl.insertParkingTime(licensePlateText.getText(), dataAndTime(timeBox.getSelectionModel().getSelectedItem()), Integer.parseInt(chargeLabel.getText()), typeVehicleBox.getValue().toString(), viewSelectSpot.getText());
			FirstPanelController.parkingSpotDAOImpl.changeStatusSpot(viewSelectSpot.getText());
			//end view
			userPanelController.endCreateClientPanel();
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("License Plate error!");
			alert.setHeaderText("Your license plate is invalid. Please try again");

			alert.showAndWait();
		}
		
		if(checkBoxCllientID.isSelected() == false) {
			FirstPanelController.clientDAOImpl.insertClient(licensePlateText.getText(), firstNameText.getText(), secondNameText.getText(), phoneNumberText.getText());	
			FirstPanelController.parkingTimeDAOImpl.insertParkingTime(licensePlateText.getText(), dataAndTime(timeBox.getSelectionModel().getSelectedItem()), Integer.parseInt(chargeLabel.getText()), typeVehicleBox.getValue().toString(), viewSelectSpot.getText());
			FirstPanelController.parkingSpotDAOImpl.changeStatusSpot(viewSelectSpot.getText());
			//end view
			userPanelController.endCreateClientPanel();			
		}	
	}

	@FXML
	void searchButton(ActionEvent event) {
		viewSelectSpot.setText(""); //clean selectSpot Label
		
		viewPriceLabel.setText(Integer.toString(FirstPanelController.typeVehicleDAOImpl.getPrice(typeVehicleBox.getValue().toString())));

		parkingPlaceColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberSpotProperty());
		parkingPlaceColumn.setStyle("-fx-alignment: CENTER;");
		statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
		statusColumn.setStyle("-fx-alignment: CENTER;");

		pPlaceTable.setItems(FirstPanelController.parkingSpotDAOImpl.getAllParkingSpot(typeVehicleBox.getValue().toString()));
	}
	
    @FXML
    void selectSpotButton(ActionEvent event) {	
    	if(pPlaceTable.getSelectionModel().getSelectedIndex() >= 0)
    	viewSelectSpot.setText(pPlaceTable.getSelectionModel().getSelectedItem().getNumberSpot());
    }

    @FXML
    void calculatePriceButton(ActionEvent event) {
    	chargeLabel.setText(Integer.toString(Integer.parseInt(viewPriceLabel.getText()) * timeBox.getSelectionModel().getSelectedItem()));
    }
    
    @FXML
    void checkBoxCllient(ActionEvent event) {
		if(checkBoxCllientID.isSelected() == false) {
			licensePlateText.disableProperty().bind(Bindings.isEmpty(phoneNumberText.textProperty()) .or(Bindings.isEmpty(viewSelectSpot.textProperty())));
		}else {
			licensePlateText.disableProperty().bind(Bindings.isEmpty(viewSelectSpot.textProperty()));
		}
    }
    
	public void initialize(URL arg0, ResourceBundle arg1) {
		timeBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 24);
		//// Metoda toString potem może zrobie konwerter
		ArrayList<TypeVehicle> typeV = FirstPanelController.typeVehicleDAOImpl.getAllTypeVehicle();
		typeVehicleBox.getItems().addAll(typeV);
		
		//bind property
		searchButtonID.disableProperty().bind(BooleanExpression.booleanExpression(this.typeVehicleBox.getSelectionModel().selectedItemProperty().isNull()));
		firstNameText.disableProperty().bind((BooleanProperty.booleanProperty(this.checkBoxCllientID.selectedProperty()) .or(Bindings.isEmpty(viewSelectSpot.textProperty()))));
		secondNameText.disableProperty().bind(Bindings.isEmpty(firstNameText.textProperty()) .or(Bindings.isEmpty(viewSelectSpot.textProperty()) .or(BooleanProperty.booleanProperty(this.checkBoxCllientID.selectedProperty()))));
		phoneNumberText.disableProperty().bind(Bindings.isEmpty(secondNameText.textProperty()).or(Bindings.isEmpty(viewSelectSpot.textProperty()) .or(BooleanProperty.booleanProperty(this.checkBoxCllientID.selectedProperty()))));	
		licensePlateText.disableProperty().bind(Bindings.isEmpty(phoneNumberText.textProperty()) .or(Bindings.isEmpty(viewSelectSpot.textProperty())));
		timeBox.disableProperty().bind(Bindings.isEmpty(licensePlateText.textProperty()).or(Bindings.isEmpty(viewSelectSpot.textProperty())));
		calculatePriceButtonID.disableProperty().bind(BooleanExpression.booleanExpression(this.timeBox.getSelectionModel().selectedItemProperty().isNull()) .or(Bindings.isEmpty(viewSelectSpot.textProperty())));
		selectSpotButtonID.disableProperty().bind(BooleanExpression.booleanExpression(this.pPlaceTable.getSelectionModel().selectedItemProperty().isNull()));
		nextPageButtonID.disableProperty().bind(Bindings.isEmpty(chargeLabel.textProperty()) .or(Bindings.isEmpty(viewSelectSpot.textProperty())));

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
