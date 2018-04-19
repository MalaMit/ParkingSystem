package databaseModel;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ParkingTime {
	
	private StringProperty clientLicensePlate;
	private StringProperty timestamp;
	private StringProperty datetime;
	private FloatProperty bill;
	private StringProperty typeVehicle;
	private StringProperty parkingNumber;
	
	public ParkingTime() {
		this.clientLicensePlate = new SimpleStringProperty();
		this.timestamp = new SimpleStringProperty();
		this.datetime = new SimpleStringProperty();
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
	 * timestamp
	 */
	public String getTimeStamp() {
		return timestamp.get();
	}

	public void setTimestamp(String timestamp) {
		this.timestamp.set(timestamp);
	}
	
	public StringProperty getTimeStampProperty() {
		return timestamp;
	}

	/**
	 * datetime
	 */
	public String getDatetime() {
		return datetime.get();
	}

	public void setDatetime(String datetime) {
		this.datetime.set(datetime);
	}
	
	public StringProperty getDatetimeProperty() {
		return datetime;
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
