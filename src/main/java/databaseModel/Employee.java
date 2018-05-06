package databaseModel;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {

	private StringProperty name;
    private StringProperty surname;
    private LongProperty pesel;
    private StringProperty phone;
    private StringProperty dateEmployed;
    private FloatProperty hourlyWage;
    private StringProperty position;

    public Employee(){

    	this.name = new SimpleStringProperty();
    	this. surname = new SimpleStringProperty();
    	this.pesel = new SimpleLongProperty();
    	this.phone = new SimpleStringProperty();
    	this. hourlyWage = new SimpleFloatProperty();
    	this.position = new SimpleStringProperty();
    	this. dateEmployed = new SimpleStringProperty();

    }
    
	/**
	 * name
	 */
    public void setName(String name) {
    	this.name.set(name);
    }
    
    public String getName() {
    	return name.get();
    }
    
	public StringProperty getNameProperty() {
		return name;
	}

	/**
	 * surname
	 */
    public void setSurname(String surname) {
    	this.surname.set(surname);
    }
    
    public String getSurname() {
    	return surname.get();
    }
    
	public StringProperty getSurnameProperty() {
		return surname;
	}

	/**
	 * pesel
	 */
    public void setPesel(Long pesel) {
    	this.pesel.set(pesel);
    }
    
    public Long getPesel() {
    	return pesel.get();
    }
    
	public LongProperty getPeselProperty() {
		return pesel;
	}

	/**
	 * phone
	 */
    public void setPhone(String phone) {
    	this.phone.set(phone);
    }
    
    public String getPhone() {
    	return phone.get();
    }
    
	public StringProperty getPhoneProperty() {
		return phone;
	}

	/**
	 * dateEmployed
	 */
    public void setDateEmployed(String dateEmployed) {
    	this.dateEmployed.set(dateEmployed);
    }
    
    public String getDateEmployed() {
    	return dateEmployed.get();
    }
    
	public StringProperty getDateEmployedProperty() {
		return dateEmployed;
	}

	/**
	 * hourlyWage
	 */
    public void setHourlyWage(Float hourlyWage) {
    	this.hourlyWage.set(hourlyWage);
    }
    
    public Float getHourlyWage() {
    	return hourlyWage.get();
    }
    
	public FloatProperty getHourlyWageProperty() {
		return hourlyWage;
	}

	/**
	 * position
	 */
    public void setPosition(String position) {
    	this.position.set(position);
    }
    
    public String getPosition() {
    	return position.get();
    }
    
	public StringProperty getPositionProperty() {
		return position;
	}
  
}
