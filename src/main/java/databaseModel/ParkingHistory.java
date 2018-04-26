package databaseModel;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ParkingHistory {
	
	private IntegerProperty historyParkingID;
	private StringProperty timeIn;
	private StringProperty timeOut;
	private FloatProperty bill;
	private StringProperty clientLicensePlate;
	private StringProperty parkingNumber;
	
	public ParkingHistory() {
		this.historyParkingID = new SimpleIntegerProperty();
		this.timeIn = new SimpleStringProperty();
		this.timeOut = new SimpleStringProperty();
		this.bill = new SimpleFloatProperty();
		this.clientLicensePlate = new SimpleStringProperty();
		this.parkingNumber = new SimpleStringProperty();
	}
	
	/**
	 * @return the historyParkingID
	 */
	public int getHistoryParkingID() {
		return historyParkingID.get();
	}
	
	public IntegerProperty getHistoryParkingProperty() {
		return historyParkingID;
	}
	/**
	 * @param historyParkingID the historyParkingID to set
	 */
	public void setHistoryParkingID(int historyParkingID) {
		this.historyParkingID.set(historyParkingID);
	}
	/**
	 * @return the timeIn
	 */
	public String getTimeIn() {
		return timeIn.get();
	}
	
	public StringProperty getTimeInProperty() {
		return timeIn;
	}
	/**
	 * @param timeIn the timeIn to set
	 */
	public void setTimeIn(String timeIn) {
		this.timeIn.set(timeIn);
	}
	/**
	 * @return the timeOut
	 */
	public String getTimeOut() {
		return timeOut.get();
	}
	
	public StringProperty getTimeOutProperty() {
		return timeOut;
	}
	/**
	 * @param timeOut the timeOut to set
	 */
	public void setTimeOut(String timeOut) {
		this.timeOut.set(timeOut);
	}
	/**
	 * @return the bill
	 */
	public float getBill() {
		return bill.get();
	}
	
	public FloatProperty getBillProperty() {
		return bill;
	}
	/**
	 * @param bill the bill to set
	 */
	public void setBill(float bill) {
		this.bill.set(bill);
	}
	/**
	 * @return the clientLicensePlate
	 */
	public String getClientLicensePlate() {
		return clientLicensePlate.get();
	}
	
	public StringProperty getClientLicensePlateProperty() {
		return clientLicensePlate;
	}
	/**
	 * @param clientLicensePlate the clientLicensePlate to set
	 */
	public void setClientLicensePlate(String clientLicensePlate) {
		this.clientLicensePlate.set(clientLicensePlate);
	}
	/**
	 * @return the parkingNumber
	 */
	public String getParkingNumber() {
		return parkingNumber.get();
	}
	
	public StringProperty getParkingNumberProperty() {
		return parkingNumber;
	}
	/**
	 * @param parkingNumber the parkingNumber to set
	 */
	public void setParkingNumber(String parkingNumber) {
		this.parkingNumber.set(parkingNumber);
	}	

}
