package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import databaseModel.Client;
import javafx.beans.binding.BooleanExpression;
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

public class ClientsViewForAdminController implements Initializable {

	private AdminPanelAccessController adminPanelAccessController;

	private ObservableList<Client> clientList = CreateClientController.clientDAOImpl.getAllClient();

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
		Parent parent = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			parent = loader.load(getClass().getResource("/fxml/ClientHistoryForAdmin.fxml").openStream());

			ClientHistoryForAdminController clientHistoryForAdminController = (ClientHistoryForAdminController) loader
					.getController();
			clientHistoryForAdminController
					.setLicensePlate(clientTable.getSelectionModel().getSelectedItem().getLicensePlate());

			Scene scene = new Scene(parent);

			Stage window = new Stage();

			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Client History");
			window.setResizable(false);
			window.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void searchClient(KeyEvent event) {
		searchClientID.textProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate((Predicate<? super Client>) (Client client) -> {
				if (newValue.isEmpty() || newValue == null) {
					return true;
				} else if (client.getLicensePlate().toLowerCase().contains(newValue.toLowerCase().trim())) {
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
		
		clientHistoryButtonID.disableProperty().bind(BooleanExpression.booleanExpression(this.clientTable.getSelectionModel().selectedItemProperty().isNull()));

	}

	public void populateTable(ObservableList<Client> clientObservableList) {
		clientTable.setItems(clientObservableList);
	}

	public void setClientsViewForAdmin(AdminPanelAccessController adminPanelAccessController) {
		this.adminPanelAccessController = adminPanelAccessController;
	}

}
