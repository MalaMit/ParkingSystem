package databaseDAO;

import databaseModel.ParkingTime;
import javafx.collections.ObservableList;

public interface ParkingTimeDAO {
	
	ObservableList<ParkingTime> getAllTakenSPot();
	
	void insertParkingTime(String clientLicensePlate, String timeOut, int bill, String typeVehicle, String parkingNumber);
	boolean deleteParkingTime();
	boolean updateParkingTime();

}
