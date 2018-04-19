package applicationController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import databaseDAOImpl.ParkingSpotDAOImpl;
import databaseDAOImpl.TypeVehicleDAOImpl;
import databaseModel.ParkingSpot;
import databaseModel.TypeVehicle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;

public class CarTypeAndSpaceController implements Initializable {

	private UserPanelController userPanelController;
	private ParkingSpotDAOImpl parkingSpotDAOImpl = new ParkingSpotDAOImpl();
	private TypeVehicleDAOImpl typeVehicleDAOImpl = new TypeVehicleDAOImpl();

	@FXML
	private JFXComboBox<TypeVehicle> typeVehicleCBox;

	@FXML
	void nextPage(ActionEvent event) {
		userPanelController.personalDatePanel();;
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		////Metoda toString potem może zrobie konwerter
		ArrayList<TypeVehicle> typeV = typeVehicleDAOImpl.getAllTypeVehicle();	
		typeVehicleCBox.getItems().addAll(typeV);
		
		
		String a = "Motocykl";
		ObservableList<ParkingSpot> parkingS = parkingSpotDAOImpl.getAllParkingSpot(a);
		System.out.println(parkingS);
	}
	

	public void setUserPanelController(UserPanelController userPanelController) {
		this.userPanelController = userPanelController;
	}

}
