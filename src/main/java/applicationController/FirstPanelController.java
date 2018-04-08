package applicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FirstPanelController implements Initializable {
	
	@FXML
	private void userPanelButton(ActionEvent event) {
		Parent parent1 = null;
		try {
			parent1 = FXMLLoader.load(getClass().getResource("/fxml/UserPanel.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(parent1);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(scene);
		window.show();
	}

	@FXML
	private void adminLoginButton(ActionEvent event) {

/*		DBUtil mysqlConnect = new DBUtil();
 		String sql = "SELECT * FROM `admin`";
		try {
		    PreparedStatement statement = mysqlConnect.dbConnect().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString("login") + " " + rs.getString("password"));
            }
            rs.close();
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    mysqlConnect.dbDisconnect();
}
*/
		
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/AdminLoginPanel.fxml"));
			Scene scene = new Scene(parent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}


}
