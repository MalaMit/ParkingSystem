package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import databaseDAOImpl.EmployeeDAOImpl;
import databaseModel.Employee;
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

public class EmployeePanelController implements Initializable {

		private AdminPanelAccessController adminPanelAccessController;
		static EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl(); 
		private ObservableList<Employee> employeeList;
		private FilteredList<Employee> filter;
	
		@FXML
		private TableView<Employee> employeeTable;
	
		@FXML
	    private TableColumn<Employee, String> columnName;

	    @FXML
	    private TableColumn<Employee, String> columnSurname;

	    @FXML
	    private TableColumn<Employee, Long> columnPesel;

	    @FXML
	    private TableColumn<Employee, String> columnPhone;

	    @FXML
	    private TableColumn<Employee, String> columnDataEmployee;

	    @FXML
	    private TableColumn<Employee, Float> columnHourlyWage;

	    @FXML
	    private TableColumn<Employee, String> columnJobPost;

	    @FXML
	    private JFXButton deleteEmployeeButtonID;

	    @FXML
	    private JFXButton showShiftsButtonID;

	    @FXML
	    private JFXButton addNewEmployeeButtonID;

	    @FXML
	    private JFXTextField searchEmployeeID;

	    @FXML
	    void addNewEmployeeButton(ActionEvent event) {
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("/fxml/AddNewEmployee.fxml"));
				Scene scene = new Scene(parent);

				Stage window = new Stage();
				
				window.setScene(scene);
				window.initModality(Modality.APPLICATION_MODAL);
				window.setTitle("Add Employee");
				window.setResizable(false);
				window.showAndWait();

			} catch (IOException e) {
				e.printStackTrace();
			}
			refresh();

			employeeList = employeeDAOImpl.getAllAmployee();
	    }

	    @FXML
	    void deleteEmployeeButton(ActionEvent event) {
	    	employeeDAOImpl.deleteEmployee(employeeTable.getSelectionModel().getSelectedItem().getPesel());
	    	refresh();
	    }

	    @FXML
	    void searchClient(KeyEvent event) {
	    	searchEmployeeID.textProperty().addListener((observable, oldValue, newValue) -> {
				filter.setPredicate((Predicate<? super Employee>) (Employee employee) -> {
					if (newValue.isEmpty() || newValue == null) {
						return true;
					} else if (employee.getPesel().toString().contains(newValue.toLowerCase().trim())) {
						return true;
					}
					return false;
				});
			});

			SortedList<Employee> sort = new SortedList<>(filter);
			sort.comparatorProperty().bind(employeeTable.comparatorProperty());
			populateTable(sort);
	    }

	    @FXML
	    void showShiftsButton(ActionEvent event) {
			Parent parent = null;
			try {
				FXMLLoader loader = new FXMLLoader();
				parent = loader.load(getClass().getResource("/fxml/EmployeeShiftWork.fxml").openStream());

				EmployeeShiftWorkController employeeShiftWorkController = (EmployeeShiftWorkController) loader
						.getController();
				employeeShiftWorkController
						.setPesel(String.valueOf(employeeTable.getSelectionModel().getSelectedItem().getPesel()));

				Scene scene = new Scene(parent);

				Stage window = new Stage();

				window.setScene(scene);
				window.initModality(Modality.APPLICATION_MODAL);
				window.setTitle("Employee Work Time");
				window.setResizable(false);
				window.showAndWait();

			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		columnName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		columnSurname.setCellValueFactory(cellData -> cellData.getValue().getSurnameProperty());
		columnPesel.setCellValueFactory(cellData -> cellData.getValue().getPeselProperty().asObject());
		columnPhone.setCellValueFactory(cellData -> cellData.getValue().getPhoneProperty());
		columnDataEmployee.setCellValueFactory(cellData -> cellData.getValue().getDateEmployedProperty());
		columnHourlyWage.setCellValueFactory(cellData -> cellData.getValue().getHourlyWageProperty().asObject());
		columnJobPost.setCellValueFactory(cellData -> cellData.getValue().getPositionProperty());
		
		refresh();
		
		deleteEmployeeButtonID.disableProperty().bind(BooleanExpression.booleanExpression(this.employeeTable.getSelectionModel().selectedItemProperty().isNull()));
		showShiftsButtonID.disableProperty().bind(BooleanExpression.booleanExpression(this.employeeTable.getSelectionModel().selectedItemProperty().isNull()));
	}
	
	public void setEmployeePanel(AdminPanelAccessController adminPanelAccessController) {
		this.adminPanelAccessController = adminPanelAccessController;
	}
	
	public void populateTable(ObservableList<Employee> employeeObservableList) {
		employeeTable.setItems(employeeObservableList);
	}
	
	public void refresh() {
		employeeTable.setItems(employeeDAOImpl.getAllAmployee());
		employeeList = employeeDAOImpl.getAllAmployee();
		filter = new FilteredList<>(employeeList, e -> true);
	} 
}
