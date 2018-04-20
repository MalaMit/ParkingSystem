package applicationController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class EndCreateClientController implements Initializable {	
	
	private UserPanelController userPanelController;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setUserPanelController(UserPanelController userPanelController) {
		this.userPanelController = userPanelController;
	}
}
