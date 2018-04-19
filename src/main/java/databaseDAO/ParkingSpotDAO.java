package databaseDAO;

import databaseModel.ParkingSpot;
import javafx.collections.ObservableList;

public interface ParkingSpotDAO {
	
	ObservableList <ParkingSpot> getAllParkingSpot(String typ);
	void changeStatusSpot( String numberSpot);

    boolean insertParkingSpot();
    boolean updateParkingSpot();
    boolean deleteParkingSpot();
}
