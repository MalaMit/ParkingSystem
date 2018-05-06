package databaseDAO;

import java.util.ArrayList;

import databaseModel.TypeVehicle;
import javafx.collections.ObservableList;


public interface TypeVehicleDAO {
	
	int getPrice(String type);
	
	ArrayList <TypeVehicle> getAllTypeVehicle();
	ObservableList<TypeVehicle> getPriceAndNumber();
	
	boolean insertTypeVehicle(String typeVehicle, int priceForHour);
    boolean updatePriceTypeVehicle(String typeVehicle, int priceForHour);
    boolean deleteTypeVehicle(String typeVehicle);

	boolean checkTypeVehicleIsNotUse(String typeVehicle);
	boolean checkTypeVehicleIsExist(String typeVehicle);
			
}
