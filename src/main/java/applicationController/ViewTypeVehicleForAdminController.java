package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import databaseModel.TypeVehicle;
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

public class ViewTypeVehicleForAdminController implements Initializable{
	
    @FXML
    private TableView<TypeVehicle> typeVehicleTable;
    
    @FXML
    private TableColumn<TypeVehicle, String> columnTypeVehicle;

    @FXML
    private TableColumn<TypeVehicle, Integer> columnPriceForHour;

    @FXML
    private JFXButton addTypeVehButtonID;

    @FXML
    private JFXButton deleteTypeButtonID;

    @FXML
    private JFXButton changeCostButtonID;

    @FXML
    void addNewTypeVehButton(ActionEvent event) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/AddNewTypeVehicle.fxml"));
			Scene scene = new Scene(parent);

			Stage window = new Stage();
			
			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Add New Type Vehicle");
			window.setResizable(false);
			window.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
		refreshTable();
    }

    @FXML
    void changeCostButton(ActionEvent event) {

    	Parent parent = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			parent = loader.load(getClass().getResource("/fxml/TypeVehicleChangePrice.fxml").openStream());
			TypeVehicleChangePriceController typeVehicleChangePriceController = (TypeVehicleChangePriceController) loader
					.getController();
			typeVehicleChangePriceController
					.setTypeVehicle(typeVehicleTable.getSelectionModel().getSelectedItem().getType());

			Scene scene = new Scene(parent);

			Stage window = new Stage();
			
			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Change Price");
			window.setResizable(false);
			window.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
		refreshTable();
 
    	
    }

    @FXML
    void closeButton(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void deleteTypeButton(ActionEvent event) {
    	if(UserPanelController.typeVehicleDAOImpl.checkTypeVehicleIsNotUse(typeVehicleTable.getSelectionModel().getSelectedItem().getType()) != true) {
	    	UserPanelController.typeVehicleDAOImpl.deleteTypeVehicle(typeVehicleTable.getSelectionModel().getSelectedItem().getType());
	    	refreshTable();
    	}
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		columnTypeVehicle.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
		columnPriceForHour.setCellValueFactory(cellData -> cellData.getValue().getPriceForHourProperty().asObject());
		
		refreshTable();
		
		changeCostButtonID.disableProperty().bind(BooleanExpression.booleanExpression(this.typeVehicleTable.getSelectionModel().selectedItemProperty().isNull()));
		deleteTypeButtonID.disableProperty().bind(BooleanExpression.booleanExpression(this.typeVehicleTable.getSelectionModel().selectedItemProperty().isNull()));
		
	}
	
	public void refreshTable() {
		typeVehicleTable.setItems(UserPanelController.typeVehicleDAOImpl.getPriceAndNumber());
	}

}
