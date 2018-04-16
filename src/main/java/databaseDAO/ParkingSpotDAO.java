package databaseDAO;

import databaseModel.ParkingSpot;
import javafx.collections.ObservableList;

public interface ParkingSpotDAO {
	
	ObservableList <ParkingSpot> getAllParkingSpot(String typ);

    boolean insertParkingSpot(ParkingSpot parkingSpot);
    boolean updateParkingSpot(ParkingSpot parkingSpot);
    boolean deleteParkingSpot(ParkingSpot parkingSpot);
}
