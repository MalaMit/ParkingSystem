package databaseModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admin {

	private IntegerProperty adminID;
	private StringProperty firstName;
	private StringProperty secondName;
	private StringProperty login;
	private StringProperty password;

	public Admin() {
		this.adminID = new SimpleIntegerProperty();
		this.firstName = new SimpleStringProperty();
		this.secondName = new SimpleStringProperty();
		this.login = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
	}
////AdminID
	public int getAdminID() {
		return adminID.get();
	}
	
	public void setAdminnID (int adminID) {
		this.adminID.set(adminID);
	}

	public IntegerProperty getAdminIDProperty() {
		return adminID;
	}
	
/////secondName
	public String getSecondName() {
		return secondName.get();
	}
	
	public void setSecondName (String secondName) {
		this.secondName.set(secondName);
	}
	public StringProperty getSecondNameProperty() {
		return secondName;
	}
	
/////FirstName
	public String getFirstName() {
		return firstName.get();
	}
	
	public void setFirstName (String firstName) {
		this.firstName.set(firstName);
	}
	public StringProperty getFirstNameProperty() {
		return firstName;
	}

////Login
	public String getLogin() {
		return login.get();
	}
	
	public void setLogin (String login) {
		this.login.set(login);
	}
	
	public StringProperty getLoginProperty() {
		return login;
	}

////Password
	public String getPassword() {
		return password.get();
	}
	
	public void setPassword (String password) {
		this.password.set(password);
	}
	
	public StringProperty getPasswordProperty() {
		return password;
	}


}
