package databaseModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admin {

	private IntegerProperty adminID;
	private StringProperty firstName;
	private StringProperty login;
	private StringProperty password;

	public Admin() {
		this.adminID = new SimpleIntegerProperty();
		this.firstName = new SimpleStringProperty();
		this.login = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
	}

	public IntegerProperty getAdminID() {
		return adminID;
	}

	public void setAdminID(IntegerProperty adminID) {
		this.adminID = adminID;
	}

	public StringProperty getFirstName() {
		return firstName;
	}

	public void setFirstName(StringProperty firstName) {
		this.firstName = firstName;
	}

	public StringProperty getLogin() {
		return login;
	}

	public void setLogin(StringProperty login) {
		this.login = login;
	}

	public StringProperty getPassword() {
		return password;
	}

	public void setPassword(StringProperty password) {
		this.password = password;
	}
}
