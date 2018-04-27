package applicationController;

import java.net.URL;
import java.util.ResourceBundle;

import databaseModel.Admin;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;

public class AdminAccountPanelController implements Initializable{
		
	private AdminPanelAccessController adminPanelAccessController;
	
    @FXML
    private TableView<Admin> adminTableView;

    @FXML
    private TableColumn<Admin, Integer> columnID;

    @FXML
    private TableColumn<Admin, String> columnLogin;

    @FXML
    private TableColumn<Admin, String> columnPassword;

    @FXML
    private TableColumn<Admin, String> columnFirstName;

    @FXML
    private TableColumn<Admin, String> columnSecondName;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		columnID.setCellValueFactory(cellData -> cellData.getValue().getAdminIDProperty().asObject());
		columnID.setStyle("-fx-alignment: CENTER;");
		columnLogin.setCellValueFactory(cellData -> cellData.getValue().getLoginProperty());
		columnLogin.setStyle("-fx-alignment: CENTER;");
		columnPassword.setCellValueFactory(cellData -> cellData.getValue().getPasswordProperty());
		columnPassword.setStyle("-fx-alignment: CENTER;");
		columnFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
		columnFirstName.setStyle("-fx-alignment: CENTER;");
		columnSecondName.setCellValueFactory(cellData -> cellData.getValue().getSecondNameProperty());
		columnSecondName.setStyle("-fx-alignment: CENTER;");
		
		adminTableView.setItems(FirstPanelController.adminDAOImpl.getAllAdmin());
		
	}

	public void setAdminMainStackPane(AdminPanelAccessController adminPanelAccessController) {
		this.adminPanelAccessController = adminPanelAccessController;
	}
}

