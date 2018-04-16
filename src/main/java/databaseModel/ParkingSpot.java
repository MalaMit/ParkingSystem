package databaseModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ParkingSpot {
	
	private IntegerProperty parkingSpotID;
	private StringProperty numberSpot;
	private StringProperty status;
	private SimpleObjectProperty<TypeVehicle> typeVehicle;
	
	public ParkingSpot() {
		this.parkingSpotID = new SimpleIntegerProperty();
		this.numberSpot = new SimpleStringProperty();
		this.status = new SimpleStringProperty();
		this.typeVehicle = new SimpleObjectProperty<>();
	}
	
	///parkingSpotID
	public  int getParkingSpotID() {
		return parkingSpotID.get();
	}
	
	public void setParkingSpotID( int parkingSpotID) {
		this.parkingSpotID.set(parkingSpotID);
	}
	
	public IntegerProperty parkingSpotID() {
		return parkingSpotID;
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
	
	////TypeVehicle
	public TypeVehicle getTypeVehicle() {
		return typeVehicle.get();
	}
	
	public void setTypeVehicle( TypeVehicle typeVehicle) {
		this.typeVehicle.set(typeVehicle);
	}
	
	public SimpleObjectProperty<TypeVehicle> getTypeVehicleProperty() {
		return typeVehicle;
	}
	
	public String toString() {
		return numberSpot.get() +" "+  status.get() + " " + typeVehicle.get();
	}
}
