package databaseDAO;

import java.util.ArrayList;

import databaseModel.TypeVehicle;


public interface TypeVehicleDAO {
	
	int getPrice(String type);
	
	ArrayList <TypeVehicle> getAllTypeVehicle();
			
}
