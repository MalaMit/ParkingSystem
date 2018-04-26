package databaseDAO;


import databaseModel.ParkingTime;
import javafx.collections.ObservableList;

public interface ParkingTimeDAO {
	
	ObservableList<ParkingTime> getAllTakenSPot();
	ObservableList<ParkingTime> getToExitParking(String licensePlate);
	boolean checkLicensePlateExist(String licensePlate);
	int getOverrunTimePrice(String licensePlate);
	
	void insertParkingTime(String clientLicensePlate, String timeOut, int bill, String typeVehicle, String parkingNumber);
	boolean deleteParkingTime(String licensePlate);

}
