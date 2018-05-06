package databaseDAO;

import databaseModel.ParkingSpot;
import javafx.collections.ObservableList;

public interface ParkingSpotDAO {
	
	ObservableList<ParkingSpot> getAllParkingSpot();
	ObservableList <ParkingSpot> getAllFreeParkingSpot(String typ);
	void changeStatusSpot( String numberSpot);
	void changeStatusSpotExit(String numberSpot);
	
	boolean checkNumberFree(String number);
	boolean insertParkingSpot(String number, String typevehicle);
    boolean deleteParkingSpot(String numberSpot);
}
