package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;

import applicationStart.Main;
import dataValidation.DataValidation;
import databaseDAOImpl.ClientDAOImpl;
import databaseDAOImpl.ParkingSpotDAOImpl;
import databaseDAOImpl.ParkingTimeDAOImpl;
import databaseDAOImpl.TypeVehicleDAOImpl;
import databaseModel.ParkingSpot;
import databaseModel.TypeVehicle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class CreateClientController implements Initializable {

	static TypeVehicleDAOImpl typeVehicleDAOImpl = new TypeVehicleDAOImpl();
	static ParkingSpotDAOImpl parkingSpotDAOImpl = new ParkingSpotDAOImpl();
	static ClientDAOImpl clientDAOImpl = new ClientDAOImpl();
	static ParkingTimeDAOImpl parkingTimeDAOImpl = new ParkingTimeDAOImpl();
	private ArrayList<TypeVehicle> typeV = typeVehicleDAOImpl.getAllTypeVehicle();
	
	private static final String exit = "/fxml/FirstPanel.fxml";
	private static final String endCreate = "/fxml/EndCreateClient.fxml";

	@FXML
	private JFXComboBox<TypeVehicle> typeVehicleClientBoxID;

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
	private TextField firstNameTextID;//^([a-zA-ZąęćżźńłóśĄĆĘŁŃÓŚŹŻ]{1,20})

	@FXML
	private TextField licensePlateTextID;//^([0-9a-zA-ZąęćżźńłóśĄĆĘŁŃÓŚŹŻ]{1,10})

	@FXML
	private TextField phoneNumberTextID;//^([+]{0,1})([0-9]{0,2}[\s]{0,1})([0-9]{0,3}[-]{0,1})([0-9]{0,3}[-]{0,1})([0-9]{0,3})

	@FXML
	private TextField secondNameTextID;//^([a-zA-ZąęćżźńłóśĄĆĘŁŃÓŚŹŻ]{1,20})

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

    @FXML
    private Label firstNameLabelID;
    
    @FXML
    private Label secondNameLabelID;

    @FXML
    private Label phoneNumberLabelID;

    @FXML
    private Label licensePlateLabelID;

	static Thread thred = new Thread();

	@FXML
	void outProgramButton(ActionEvent event) {
		loadFxml(exit);
	}

	@FXML
	void nextPage(ActionEvent event) {
		// create client
		
		if(checkBoxCllientID.isSelected() == false) {
			boolean name = DataValidation.textAlphabetWithPolishMarks(firstNameTextID, firstNameLabelID, "Name is Required (MAX 20 characters)", "2", "20");
			boolean secondName = DataValidation.textAlphabetWithPolishMarks(secondNameTextID, secondNameLabelID, "Second name is Required (MAX 20 characters)", "2", "20");
			boolean phone = DataValidation.textPhone(phoneNumberTextID, phoneNumberLabelID, "Phone is Required");
			boolean lPlate = DataValidation.textAlphabetAndNumber(licensePlateTextID, licensePlateLabelID, "License plate is Required (3-10 characters)", "3", "10");
			
			if (name && secondName && phone && lPlate) {
				if(clientDAOImpl.checkLicensePlate(licensePlateTextID.getText()) == false) {
					clientDAOImpl.insertClient(licensePlateTextID.getText(), firstNameTextID.getText(),
							secondNameTextID.getText(), phoneNumberTextID.getText());

					parkingTimeDAOImpl.insertParkingTime(licensePlateTextID.getText(),
							timeBoxID.getSelectionModel().getSelectedItem(), Integer.parseInt(chargeLabelID.getText()),
							typeVehicleClientBoxID.getSelectionModel().getSelectedItem().getType(),
							viewSelectSpotLabelID.getText());

					parkingSpotDAOImpl.changeStatusSpot(viewSelectSpotLabelID.getText());
					// end view
					loadFxml(endCreate);
				}else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("License Plate error!");
					alert.setHeaderText("Your license plate is existed. Please try again");

					alert.showAndWait();
				}
			}
			
		}
			
		if(checkBoxCllientID.isSelected() == true) {
			if(clientDAOImpl.checkLicensePlate(licensePlateTextID.getText()) == true) {
				if(parkingTimeDAOImpl.checkLicensePlateExist(licensePlateTextID.getText()) == false){
					parkingTimeDAOImpl.insertParkingTime(licensePlateTextID.getText(),
							timeBoxID.getSelectionModel().getSelectedItem(), Integer.parseInt(chargeLabelID.getText()),
							typeVehicleClientBoxID.getSelectionModel().getSelectedItem().getType(),
							viewSelectSpotLabelID.getText());
	
					parkingSpotDAOImpl.changeStatusSpot(viewSelectSpotLabelID.getText());
	
					// end view
					loadFxml(endCreate);
				}else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("License Plate error!");
					alert.setHeaderText("Your license plate is use now. Please try again");
	
					alert.showAndWait();
				}
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("License Plate error!");
				alert.setHeaderText("Your license plate is no existed. Please try again");

				alert.showAndWait();
			}
		}
	}

	@FXML
	void searchButton(ActionEvent event) {
		viewSelectSpotLabelID.setText(null); // clean selectSpot Label

		viewPriceLabelID.setText(Integer.toString(
				typeVehicleDAOImpl.getPrice(typeVehicleClientBoxID.getSelectionModel().getSelectedItem().getType())));

		parkingPlaceColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberSpotProperty());

		statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());

		pPlaceTable.setItems(parkingSpotDAOImpl
				.getAllFreeParkingSpot(typeVehicleClientBoxID.getSelectionModel().getSelectedItem().getType()));
	}

	@FXML
	void selectSpotButton(ActionEvent event) {
		timeBoxID.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 24);//time to choose
		
		if (pPlaceTable.getSelectionModel().getSelectedIndex() >= 0)
			viewSelectSpotLabelID.setText(pPlaceTable.getSelectionModel().getSelectedItem().getNumberSpot());
	}

	@FXML
	void calculatePriceButton(ActionEvent event) {
			chargeLabelID.setText(Integer.toString(
					Integer.parseInt(viewPriceLabelID.getText()) * timeBoxID.getSelectionModel().getSelectedItem()));
		
	}

	@FXML
	void checkBoxCllient(ActionEvent event) {
		chargeLabelID.setText(null);
		licensePlateTextID.setText(null);
		timeBoxID.valueProperty().set(null);

		if (!checkBoxCllientID.isSelected()) {
			licensePlateTextID.disableProperty().bind(Bindings.isEmpty(phoneNumberTextID.textProperty())
					.or(Bindings.isEmpty(viewSelectSpotLabelID.textProperty())));
		} else {
			licensePlateTextID.disableProperty().bind(Bindings.isEmpty(viewSelectSpotLabelID.textProperty()));
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		
		typeVehicleClientBoxID.setConverter(new StringConverter<TypeVehicle>() {
			@Override
			public String toString(TypeVehicle object) {
				return object.getType();
			}

			@Override
			public TypeVehicle fromString(String string) {
				return null;
			}

		});
		typeVehicleClientBoxID.getItems().addAll(typeV);
		//style
		typeVehicleClientBoxID.setStyle("-fx-background-color: White; -fx-font-size: 14;");
		
		// bind property
		searchButtonID.disableProperty().bind(BooleanExpression
				.booleanExpression(this.typeVehicleClientBoxID.getSelectionModel().selectedItemProperty().isNull()));

		firstNameTextID.disableProperty()
				.bind((BooleanProperty.booleanProperty(this.checkBoxCllientID.selectedProperty())
						.or(Bindings.isEmpty(viewSelectSpotLabelID.textProperty()))));

		secondNameTextID.disableProperty().bind(Bindings.isEmpty(firstNameTextID.textProperty())
				.or(BooleanProperty.booleanProperty(this.checkBoxCllientID.selectedProperty())));

		phoneNumberTextID.disableProperty().bind(Bindings.isEmpty(secondNameTextID.textProperty())
				.or(BooleanProperty.booleanProperty(this.checkBoxCllientID.selectedProperty())));

		licensePlateTextID.disableProperty().bind(Bindings.isEmpty(phoneNumberTextID.textProperty())
				.or(Bindings.isEmpty(viewSelectSpotLabelID.textProperty())));

		timeBoxID.disableProperty().bind(Bindings.isEmpty(licensePlateTextID.textProperty()));

		calculatePriceButtonID.disableProperty().bind(BooleanExpression
				.booleanExpression(this.timeBoxID.getSelectionModel().selectedItemProperty().isNull()));

		selectSpotButtonID.disableProperty().bind(BooleanExpression
				.booleanExpression(this.pPlaceTable.getSelectionModel().selectedItemProperty().isNull()));

		nextPageButtonID.disableProperty().bind(Bindings.isEmpty(chargeLabelID.textProperty())
				.or(Bindings.isEmpty(viewSelectSpotLabelID.textProperty())));

	}

	public void loadFxml(String pathFxml) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource(pathFxml));
			Scene scene = new Scene(parent);

			Stage window = Main.getPrimaryStage();

			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
