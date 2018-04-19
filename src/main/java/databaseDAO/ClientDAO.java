package databaseDAO;

import java.util.ArrayList;

import databaseModel.Client;
import databaseModel.TypeVehicle;
import javafx.collections.ObservableList;

public interface ClientDAO {
	
	ArrayList <TypeVehicle> getClientForLicensePlate(String licensePlate);
	ObservableList<Client> getAllClient();
	
	boolean insertClient(String licensePlate, String firstName, String secondName, String phoneNumber);
    boolean updateClient();
    boolean deleteClient();
}
