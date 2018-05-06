package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXTextField;

import databaseModel.ParkingTime;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ParkingTimeViewAdminController implements Initializable{
	
	private AdminPanelAccessController adminPanelAccessController;
	private ObservableList<ParkingTime> pkTime = UserPanelController.parkingTimeDAOImpl.getAllTakenSpot();
	private FilteredList<ParkingTime> filter = new FilteredList<>(pkTime, e -> true);
	
	
    @FXML
    private TableView<ParkingTime> viewParkingTime;

    @FXML
    private TableColumn<ParkingTime, String> columnNumber;

    @FXML
    private TableColumn<ParkingTime, String> columnVehicle;

    @FXML
    private TableColumn<ParkingTime, String> columnClieLicenPlate;

    @FXML
    private TableColumn<ParkingTime, Float> columnBill;

    @FXML
    private TableColumn<ParkingTime, String> columnTimeIn;

    @FXML
    private TableColumn<ParkingTime, String> columnTimeOut;
    
    @FXML
    private JFXTextField searchNubSpotID;

    @FXML
    void viewParkingSpot(ActionEvent event) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/ViewParkingSpotForAdmin.fxml"));
			Scene scene = new Scene(parent);

			Stage window = new Stage();
			
			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Parking Spot");
			window.setResizable(false);
			window.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void viewTypeVehicle(ActionEvent event) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/ViewTypeVehicleForAdmin.fxml"));
			Scene scene = new Scene(parent);

			Stage window = new Stage();
			
			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Type Vehicle");
			window.setResizable(false);
			window.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void searchNubSpot(KeyEvent event) {
    	searchNubSpotID.textProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate((Predicate<? super ParkingTime>) (ParkingTime parkingTime) -> {
				if (newValue.isEmpty() || newValue == null) {
					return true;
				} else if (parkingTime.getParkingNumber().toLowerCase().contains(newValue.toLowerCase().trim())) {
					return true;
				}
				return false;
			});
		});

		SortedList<ParkingTime> sort = new SortedList<>(filter);
		sort.comparatorProperty().bind(viewParkingTime.comparatorProperty());
		populateTable(sort);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		columnNumber.setCellValueFactory(cellData -> cellData.getValue().getParkingNumberProperty());
		columnVehicle.setCellValueFactory(cellData -> cellData.getValue().getTypeVehicleProperty());
		columnClieLicenPlate.setCellValueFactory(cellData -> cellData.getValue().getClientLicensePlateProperty());
		columnBill.setCellValueFactory(cellData -> cellData.getValue().getBillProperty().asObject());
		columnTimeIn.setCellValueFactory(cellData -> cellData.getValue().getTimeInProperty());
		columnTimeOut.setCellValueFactory(cellData -> cellData.getValue().getTimeOutProperty());
		
		populateTable(pkTime);
		
	}
	
	public void setParkingSpotsViewAdmin(AdminPanelAccessController adminPanelAccessController) {
		this.adminPanelAccessController = adminPanelAccessController;
	}
	
	public void populateTable(ObservableList<ParkingTime> parkingTimeObservableList) {
		viewParkingTime.setItems(parkingTimeObservableList);
	}

}
