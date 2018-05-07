package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import applicationStart.Main;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EndCreateClientController implements Initializable {

	private Timer timer = new Timer();

	private TimerTask timerTask = new TimerTask() {
		@Override
		public void run() {
			Platform.runLater(() -> {
				backToMain();
			});
		}
	};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		timer.schedule(timerTask, 3000);
	}

	public void backToMain() {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/FirstPanel.fxml"));
			Scene scene = new Scene(parent);

			Stage window = Main.getPrimaryStage();

			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
