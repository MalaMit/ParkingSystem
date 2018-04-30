package applicationController;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import databaseModel.ParkingHistory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClientHistoryForAdminController implements Initializable{
   
		@FXML
		private TableView<ParkingHistory> historyClientTable;
	
		@FXML
	    private TableColumn<ParkingHistory, Integer> columnID;

	    @FXML
	    private TableColumn<ParkingHistory, String> columnTimeIn;

	    @FXML
	    private TableColumn<ParkingHistory, String> columnTimeOut;

	    @FXML
	    private TableColumn<ParkingHistory, String> columnParkingNumber;

	    @FXML
	    private TableColumn<ParkingHistory, Float> columnBill;
	    
	    @FXML
	    private JFXTextField licensePlateFieldID;
	    
	    @FXML
	    private JFXButton closeButtonID;
	    
		private Timer timer = new Timer();

		private TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> {
					historyClientTable.setItems(FirstPanelController.parkingHistoryDAOImpl.getHistoryByLicensePlate(licensePlateFieldID.getText()));
				});
			}
		};


	    @FXML
	    void closeButton(ActionEvent event) {
	    	((Node)(event.getSource())).getScene().getWindow().hide();
	    } 
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//.substring(0, (lista.get(0).getTimeOut()).indexOf("."))
		columnID.setCellValueFactory(cellData -> cellData.getValue().getHistoryParkingIDProperty().asObject());
		columnTimeIn.setCellValueFactory(cellData -> cellData.getValue().getTimeInProperty());
		columnTimeOut.setCellValueFactory(cellData -> cellData.getValue().getTimeOutProperty());
		columnParkingNumber.setCellValueFactory(cellData -> cellData.getValue().getParkingNumberProperty());
		columnBill.setCellValueFactory(cellData -> cellData.getValue().getBillProperty().asObject());
		
		timer.schedule(timerTask, 100);
	}
	
	public void setLicensePlate(String licensePlate) {
		licensePlateFieldID.setText(licensePlate);
	}

}
