package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import databaseDAO.EmployeeDAO;
import databaseImplementDao.EmployeeImplementDAO;
import databaseModel.Employee;
import databaseUtil.EmployeesList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmployeePanelController implements Initializable {
	@FXML
	private TableView employeeTableView;
	@FXML
	private TableColumn name;
	@FXML
	private TableColumn surname;
	@FXML
	private TableColumn pesel;
	@FXML
	private TableColumn position;
	@FXML
	private TableColumn doe;
	@FXML
	private StackPane adminMainStackPane;

	@FXML
	void addButtonAction() {

		showNewModalWindow("/fxml/addEmployee.fxml", "Add Employee");

	}

	@FXML
	void adminAccountsAction(ActionEvent event) {

	}

	@FXML
	void clientsAction(ActionEvent event) {

	}

	@FXML
	void employeesAction(ActionEvent event) {

	}

	@FXML
	void parkingSpotsAction(ActionEvent event) {

	}

	@FXML
	void logOutAdmin(ActionEvent event) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/FirstPanel.fxml"));
			Scene scene = new Scene(parent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		EmployeeDAO employeeDAO = new EmployeeImplementDAO();
		EmployeesList.getInstance().setEmployeeList(employeeDAO.getAllEmployees());
		name.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		surname.setCellValueFactory(new PropertyValueFactory<Employee, String>("surname"));
		pesel.setCellValueFactory(new PropertyValueFactory<Employee, Long>("pesel"));
		position.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
		doe.setCellValueFactory(new PropertyValueFactory<Employee, String>("dateEmployed"));
		employeeTableView.setItems(EmployeesList.getInstance().getEmployeeList());

	}

	protected void showNewModalWindow(String fxmlURL, String title) {

		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setTitle(title);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
