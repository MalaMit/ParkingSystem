package databaseDAO;

import databaseModel.ParkingHistory;
import javafx.collections.ObservableList;

public interface ParkingHistoryDAO {
	
	ObservableList<ParkingHistory> getHistoryByLicensePlate(String licensePlate);
	
    boolean insertParkingHistory(String timeIN, String timeOut, String bill, String licensePlate, String parkingNumber);
}
