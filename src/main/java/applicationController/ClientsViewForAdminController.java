package applicationController;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.collections.SortableList;

import databaseModel.Client;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

public class ClientsViewForAdminController implements Initializable{
	
	private AdminPanelAccessController adminPanelAccessController;
	
	private ObservableList<Client> clientList = FirstPanelController.clientDAOImpl.getAllClient();
	
	private FilteredList<Client> filter = new FilteredList<>(clientList, e -> true);
	
    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, String> columnLicensePlate;

    @FXML
    private TableColumn<Client, String> columnFirstName;

    @FXML
    private TableColumn<Client, String> columnSecondName;

    @FXML
    private TableColumn<Client, String> columnPhoneNumber;

    @FXML
    private JFXButton clientHistoryButtonID;  

    @FXML
    private JFXTextField searchClientID;

    @FXML
    void clientHistoryButton(ActionEvent event) {

    }

    @FXML
    void searchClient(KeyEvent event) {
    	searchClientID.textProperty().addListener((observable , oldValue, newValue) ->{
    		filter.setPredicate((Predicate<? super Client>) (Client client) -> {
    			if(newValue.isEmpty() || newValue == null) {
    				return true;
    			}
    			else if(client.getLicensePlate().contains(newValue.toLowerCase()) || client.getLicensePlate().contains(newValue.toUpperCase())) {
    				return true;
    			}
    			
    			return false;
    		});
    	});
    	
    	SortedList<Client> sort = new SortedList<>(filter);
    	sort.comparatorProperty().bind(clientTable.comparatorProperty());
    	populateTable(sort);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		columnLicensePlate.setCellValueFactory(cellData -> cellData.getValue().getLicensePlateProperty());
		columnFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
		columnSecondName.setCellValueFactory(cellData -> cellData.getValue().getSecondNameProperty());
		columnPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().getPhoneNumberProperty());
		
		populateTable(clientList);
		
	}

	public void populateTable(ObservableList<Client> clientObservableList) {
		clientTable.setItems(clientObservableList);
	}
	
	public void setClientsViewForAdmin(AdminPanelAccessController adminPanelAccessController) {
		this.adminPanelAccessController = adminPanelAccessController;
	}

}
