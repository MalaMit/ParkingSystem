package databaseDAO;

import java.util.ArrayList;

import databaseModel.TypeVehicle;


public interface TypeVehicleDAO {
	
	TypeVehicle getTypeVehicle();
	TypeVehicle getPrice();
	
	ArrayList <TypeVehicle> getAllTypeVehicle();
			
}
