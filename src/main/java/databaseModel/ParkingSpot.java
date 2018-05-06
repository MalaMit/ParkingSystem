package databaseModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ParkingSpot {
	
	private StringProperty numberSpot;
	private StringProperty status;
	private StringProperty typeVehicle;
	
	public ParkingSpot() {
		this.numberSpot = new SimpleStringProperty();
		this.status = new SimpleStringProperty();
		this.typeVehicle = new SimpleStringProperty();
	}
		
	////numberSpot
	public String getNumberSpot() {
		return numberSpot.get();
	}
	
	public void setNumberSpot( String numberSpot) {
		this.numberSpot.set(numberSpot);
	}
	
	public StringProperty getNumberSpotProperty() {
		return numberSpot;
	}
	
	/////Status
	
	public String getStatus() {
		return status.get();
	}
	
	public void setStatusSpot( String status) {
		this.status.set(status);
	}
	
	public StringProperty getStatusProperty() {
		return status;
	}
	
	////TypeVehicleID
	public String getTypeVehicle() {
		return typeVehicle.get();
	}
	
	public void setTypeVehicle( String typeVehicle) {
		this.typeVehicle.set(typeVehicle);
	}
	
	public StringProperty getTypeVehicleProperty() {
		return typeVehicle;
	}
}
