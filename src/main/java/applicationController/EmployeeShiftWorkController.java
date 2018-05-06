package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import databaseDAOImpl.ShiftDAOImpl;
import databaseModel.Shift;
import javafx.application.Platform;
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

public class EmployeeShiftWorkController implements Initializable {

	static ShiftDAOImpl shiftDAOImpl = new ShiftDAOImpl();
	private Timer timer = new Timer();

	@FXML
	private TableView<Shift> shiftTable;

	@FXML
	private TableColumn<Shift, Integer> columnID;

	@FXML
	private TableColumn<Shift, String> columnShiftStart;

	@FXML
	private TableColumn<Shift, String> columnShiftEnd;

	@FXML
	private TableColumn<Shift, Long> columnPesel;

	@FXML
	private TableColumn<Shift, String> columnShiftType;

	@FXML
	private JFXButton addWorkTimeButtonID;

	@FXML
	private JFXTextField peselFieldID;

	@FXML
	void addWorkTimeButton(ActionEvent event) {
		Parent parent = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			parent = loader.load(getClass().getResource("/fxml/AddNewWorkTime.fxml").openStream());

			AddNewWorkTimeController addNewWorkTimeController = (AddNewWorkTimeController) loader
					.getController();
			addNewWorkTimeController
					.setPesel(String.valueOf(peselFieldID.getText()));

			Scene scene = new Scene(parent);

			Stage window = new Stage();

			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Add Work Time");
			window.setResizable(false);
			window.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
		refresh();
	}

	@FXML
	void closeButton(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		columnID.setCellValueFactory(cellData -> cellData.getValue().getShiftIDProperty().asObject());
		columnShiftStart.setCellValueFactory(cellData -> cellData.getValue().getShiftStartProperty());
		columnShiftEnd.setCellValueFactory(cellData -> cellData.getValue().getShiftEndProperty());
		columnPesel.setCellValueFactory(cellData -> cellData.getValue().getPeselProperty().asObject());
		columnShiftType.setCellValueFactory(cellData -> cellData.getValue().getShiftTypeProperty());

		timer.schedule(timerTask, 100);

	}

	private TimerTask timerTask = new TimerTask() {
		@Override
		public void run() {
			Platform.runLater(() -> {
				refresh();
			});
		}
	};

	public void setPesel(String pesel) {
		peselFieldID.setText(pesel);
	}
	private void refresh() {
		shiftTable.setItems(shiftDAOImpl.getAllForEmployee(peselFieldID.getText()));
	}

}