package databaseDAO;

import databaseModel.Client;
import javafx.collections.ObservableList;

public interface ClientDAO {
	
	ObservableList<Client> getAllClient();
	
	boolean checkLicensePlate(String licensePlate);
	boolean insertClient(String licensePlate, String firstName, String secondName, String phoneNumber);
}
