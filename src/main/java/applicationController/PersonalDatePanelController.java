package applicationController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;




public class PersonalDatePanelController implements Initializable {

	private UserPanelController userPanelController;
    @FXML
    void nextPage(ActionEvent event) {
    	userPanelController.carTypeAndSpacePanel();
    }

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void setUserPanelController(UserPanelController userPanelController) {
		this.userPanelController = userPanelController;
	}

}
