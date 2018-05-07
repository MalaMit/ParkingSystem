package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import databaseModel.ParkingSpot;
import javafx.beans.binding.BooleanExpression;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ViewParkingSpotForAdminController implements Initializable {
	
    @FXML
    private TableView<ParkingSpot> parkingSpotTable;

    @FXML
    private TableColumn<ParkingSpot, String> columnNumber;
    
    @FXML
    private TableColumn<ParkingSpot, String> columnStatus;

    @FXML
    private TableColumn<ParkingSpot, String> columnTypeVehicle;

    @FXML
    private JFXButton addNewSpotButtonID;

    @FXML
    private JFXButton deleteSpotButtonID;

    @FXML
    void addNewSpotButton(ActionEvent event) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/AddNewParkingSpot.fxml"));
			Scene scene = new Scene(parent);

			Stage window = new Stage();
			
			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Add New Parking Spot");
			window.setResizable(false);
			window.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
		refreshTable();
    }

    @FXML
    void deleteSpotButton(ActionEvent event) {
    	if ((parkingSpotTable.getSelectionModel().getSelectedItem().getStatus()).contains("FREE")) {
    		CreateClientController.parkingSpotDAOImpl.deleteParkingSpot(parkingSpotTable.getSelectionModel().getSelectedItem().getNumberSpot());
    		refreshTable();
    	}
    }
    
    @FXML
    void closeButton(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		columnNumber.setCellValueFactory(cellData -> cellData.getValue().getNumberSpotProperty());
		columnStatus.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
		columnTypeVehicle.setCellValueFactory(cellData -> cellData.getValue().getTypeVehicleProperty());
		
		refreshTable();	
		
		deleteSpotButtonID.disableProperty().bind(BooleanExpression.booleanExpression(this.parkingSpotTable.getSelectionModel().selectedItemProperty().isNull()));
	}

	public void refreshTable() {
		parkingSpotTable.setItems(CreateClientController.parkingSpotDAOImpl.getAllParkingSpot());
	}
	
}
