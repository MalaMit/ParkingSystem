package applicationController;

import java.net.URL;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import databaseDAOImpl.TypeVehicleDAOImpl;
import databaseModel.TypeVehicle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;

public class CarTypeAndSpaceController implements Initializable {

	private UserPanelController userPanelController;
	private TypeVehicleDAOImpl typeVehicleDAOImpl = new TypeVehicleDAOImpl();

	@FXML
	private JFXComboBox<TypeVehicle> typeVehicleCBox;

	@FXML
	void nextPage(ActionEvent event) {
		userPanelController.personalDatePanel();;
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		////Metoda toString potem może zrobie konwerter
		ObservableList<TypeVehicle> typeV = typeVehicleDAOImpl.getAllTypeVehicle();	
		typeVehicleCBox.getItems().addAll(typeV);
	}

	public void setUserPanelController(UserPanelController userPanelController) {
		this.userPanelController = userPanelController;
	}

}
