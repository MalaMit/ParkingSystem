package databaseModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {

	private StringProperty licensePlate;
	private StringProperty firstName;
	private StringProperty secondName;
	private StringProperty phoneNumber;

	public Client() {
		this.licensePlate = new SimpleStringProperty();
		this.firstName = new SimpleStringProperty();
		this.secondName = new SimpleStringProperty();
		this.phoneNumber = new SimpleStringProperty();
	}

	/**
	 * licensePlate
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate.set(licensePlate);
		;
	}

	public String getLicensePlate() {
		return licensePlate.get();
	}

	public StringProperty getLicensePlateProperty() {
		return licensePlate;
	}

	/**
	 * firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
		;
	}

	public String getFirstName() {
		return firstName.get();
	}

	public StringProperty getFirstNameProperty() {
		return firstName;
	}

	/**
	 * secondName
	 */
	public void setSecondName(String secondName) {
		this.secondName.set(secondName);
		;
	}

	public String getSecondName() {
		return secondName.get();
	}

	public StringProperty getSecondNameProperty() {
		return secondName;
	}

	/**
	 * phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
		;
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public StringProperty getPhoneNumberProperty() {
		return phoneNumber;
	}

}
