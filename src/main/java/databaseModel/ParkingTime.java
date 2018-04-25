package databaseModel;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ParkingTime {
	
	private StringProperty clientLicensePlate;
	private StringProperty timeIn;
	private StringProperty timeOut;
	private FloatProperty bill;
	private StringProperty typeVehicle;
	private StringProperty parkingNumber;
	
	public ParkingTime() {
		this.clientLicensePlate = new SimpleStringProperty();
		this.timeIn = new SimpleStringProperty();
		this.timeOut = new SimpleStringProperty();
		this.bill = new SimpleFloatProperty();
		this.typeVehicle = new SimpleStringProperty();
		this.parkingNumber = new SimpleStringProperty();
	}
	
	/**
	 * clientLicensePlate
	 */
	public String getClientLicensePlate() {
		return clientLicensePlate.get();
	}

	public void setClientLicensePlate(String clientLicensePlate) {
		this.clientLicensePlate.set(clientLicensePlate);
	}
	
	public StringProperty getClientLicensePlateProperty() {
		return clientLicensePlate;
	}

	/**
	 * timeIn
	 */
	public String getTimeIn() {
		return timeIn.get();
	}

	public void setTimeIn(String timeIn) {
		this.timeIn.set(timeIn);
	}
	
	public StringProperty getTimeInProperty() {
		return timeIn;
	}

	/**
	 * timeOut
	 */
	public String getTimeOut() {
		return timeOut.get();
	}

	public void setTimeOut(String timeOut) {
		this.timeOut.set(timeOut);
	}
	
	public StringProperty getTimeOutProperty() {
		return timeOut;
	}

	/**
	 * bill
	 */
	public float getBill() {
		return bill.get();
	}

	public void setBill(float bill) {
		this.bill.set(bill);
	}
	
	public FloatProperty getBillProperty() {
		return bill;
	}
	/**
	 * typeVehicle
	 */
	public String getTypeVehicle() {
		return typeVehicle.get();
	}

	public void setTypeVehicle(String typeVehicle) {
		this.typeVehicle.set(typeVehicle);
	}
	
	public StringProperty getTypeVehicleProperty() {
		return typeVehicle;
	}
	/**
	 * parkingNumber
	 */
	public String getParkingNumber() {
		return parkingNumber.get();
	}

	public void setParkingNumber(String parkingNumber) {
		this.parkingNumber.set(parkingNumber);
	}
	
	public StringProperty getParkingNumberProperty() {
		return parkingNumber;
	}
    
}
