package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import databaseModel.Admin;
import javafx.beans.binding.BooleanExpression;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminAccountPanelController implements Initializable {

	private AdminPanelAccessController adminPanelAccessController;

	@FXML
	private TableView<Admin> adminTableView;

	@FXML
	private TableColumn<Admin, Integer> columnID;

	@FXML
	private TableColumn<Admin, String> columnLogin;

	@FXML
	private TableColumn<Admin, String> columnFirstName;

	@FXML
	private TableColumn<Admin, String> columnSecondName;

	@FXML
	private JFXButton addNewAdminID;

	@FXML
	private JFXButton changePasswordID;

	@FXML
	private JFXButton deleteAdminID;

	@FXML
	void addNewAdminButton(ActionEvent event) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/AddNewAdminAccount.fxml"));
			Scene scene = new Scene(parent);

			Stage window = new Stage();
			
			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Add New Admin Account");
			window.setResizable(false);
			window.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
		refreshTable();
	}

	@FXML
	void changePasswordButton(ActionEvent event) {
		Parent parent = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			parent = loader.load(getClass().getResource("/fxml/AdminChangePassword.fxml").openStream());
			AdminChangePasswordController adminChangePasswordController = (AdminChangePasswordController) loader
					.getController();
			adminChangePasswordController
					.setAccountID(Integer.toString(adminTableView.getSelectionModel().getSelectedItem().getAdminID()));

			Scene scene = new Scene(parent);

			Stage window = new Stage();
			
			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Change Password");
			window.setResizable(false);
			window.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
		refreshTable();
	}

	@FXML
	void deleteAdminButton(ActionEvent event) {
		if (adminTableView.getSelectionModel().getSelectedIndex() >= 0) {
			AdminPanelAccessController.adminDAOImpl
					.deleteAdmin(adminTableView.getSelectionModel().getSelectedItem().getAdminID());
			refreshTable();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		columnID.setCellValueFactory(cellData -> cellData.getValue().getAdminIDProperty().asObject());
		columnLogin.setCellValueFactory(cellData -> cellData.getValue().getLoginProperty());
		columnFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
		columnSecondName.setCellValueFactory(cellData -> cellData.getValue().getSecondNameProperty());

		refreshTable();
		
		deleteAdminID.disableProperty().bind(BooleanExpression.booleanExpression(this.adminTableView.getSelectionModel().selectedItemProperty().isNull()));
		changePasswordID.disableProperty().bind(BooleanExpression.booleanExpression(this.adminTableView.getSelectionModel().selectedItemProperty().isNull()));

	}

	public void refreshTable() {
		adminTableView.setItems(AdminPanelAccessController.adminDAOImpl.getAllAdmin());
	}

	public void setAdminAccountPanel(AdminPanelAccessController adminPanelAccessController) {
		this.adminPanelAccessController = adminPanelAccessController;
	}
}
