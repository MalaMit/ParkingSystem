package applicationController;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.Initializable;

public class EndCreateClientController implements Initializable {

	private UserPanelController userPanelController;

	Timer timer = new Timer();

	TimerTask timerTask = new TimerTask() {
		@Override
		public void run() {
			Platform.runLater(() -> {
				userPanelController.backToMain();
			});
		}
	};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		timer.schedule(timerTask, 3000);
	}

	public void setUserPanelController(UserPanelController userPanelController) {
		this.userPanelController = userPanelController;
	}

}
