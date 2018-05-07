package applicationController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;

import applicationStart.Main;
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

public class CreateClientController implements Initializable {

	static TypeVehicleDAOImpl typeVehicleDAOImpl = new TypeVehicleDAOImpl();
	static ParkingSpotDAOImpl parkingSpotDAOImpl = new ParkingSpotDAOImpl();
	static ClientDAOImpl clientDAOImpl = new ClientDAOImpl();
	static ParkingTimeDAOImpl parkingTimeDAOImpl = new ParkingTimeDAOImpl();

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
	void outProgramButton(ActionEvent event) {
		backToMain();
	}
	
	@FXML
	void nextPage(ActionEvent event) {
		// create client
		if (checkBoxCllientID.isSelected() == false) {

			clientDAOImpl.insertClient(licensePlateTextID.getText(), firstNameTextID.getText(),
					secondNameTextID.getText(), phoneNumberTextID.getText());

			parkingTimeDAOImpl.insertParkingTime(licensePlateTextID.getText(),
					dataAndTime(timeBoxID.getSelectionModel().getSelectedItem()),
					Integer.parseInt(chargeLabelID.getText()), typeVehicleClientBoxID.getValue().toString(),
					viewSelectSpotLabelID.getText());

			parkingSpotDAOImpl.changeStatusSpot(viewSelectSpotLabelID.getText());

			// end view
			endCreateClientPanel();
		} else {
			if ((checkBoxCllientID.isSelected()) != false
					&& (clientDAOImpl.checkLicensePlate(licensePlateTextID.getText())) != false) {

				parkingTimeDAOImpl.insertParkingTime(licensePlateTextID.getText(),
						dataAndTime(timeBoxID.getSelectionModel().getSelectedItem()),
						Integer.parseInt(chargeLabelID.getText()), typeVehicleClientBoxID.getValue().toString(),
						viewSelectSpotLabelID.getText());

				parkingSpotDAOImpl.changeStatusSpot(viewSelectSpotLabelID.getText());

				// end view
				endCreateClientPanel();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("License Plate error!");
				alert.setHeaderText("Your license plate is invalid. Please try again");

				alert.showAndWait();
			}
		}
	}

	@FXML
	void searchButton(ActionEvent event) {
		viewSelectSpotLabelID.setText(""); // clean selectSpot Label

		viewPriceLabelID.setText(Integer.toString(
				typeVehicleDAOImpl.getPrice(typeVehicleClientBoxID.getValue().toString())));

		parkingPlaceColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberSpotProperty());

		statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());

		pPlaceTable.setItems(parkingSpotDAOImpl
				.getAllFreeParkingSpot(typeVehicleClientBoxID.getValue().toString()));
	}

	@FXML
	void selectSpotButton(ActionEvent event) {
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
		
		if (checkBoxCllientID.isSelected() == false) {
			licensePlateTextID.disableProperty().bind(Bindings.isEmpty(phoneNumberTextID.textProperty())
					.or(Bindings.isEmpty(viewSelectSpotLabelID.textProperty())));
		} else {
			licensePlateTextID.disableProperty().bind(Bindings.isEmpty(viewSelectSpotLabelID.textProperty()));
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {

		timeBoxID.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 24);

		typeVehicleClientBoxID.getItems().addAll(typeVehicleDAOImpl.getAllTypeVehicle());

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

	public void backToMain() {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/FirstPanel.fxml"));
			Scene scene = new Scene(parent);

			Stage window = Main.getPrimaryStage();

			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void endCreateClientPanel() {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/EndCreateClient.fxml"));
			Scene scene = new Scene(parent);

			Stage window = Main.getPrimaryStage();

			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String dataAndTime(int addHour) {
		LocalDateTime localTime = LocalDateTime.now().plusHours(addHour);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = localTime.format(formatter);
		return formatDateTime;
	}

}
