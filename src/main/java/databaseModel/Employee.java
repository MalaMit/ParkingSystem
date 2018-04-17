package databaseModel;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {
	private SimpleStringProperty name;
    private SimpleStringProperty surname;
    public SimpleLongProperty pesel;
    public SimpleStringProperty idSeriesNumber;
    public SimpleStringProperty dateEmployed;
    public SimpleFloatProperty hourlyWage;
    public SimpleStringProperty position;
    public String login;
    public String passHash;
    public int phone;

    public Employee(){

        name = new SimpleStringProperty();
        surname = new SimpleStringProperty();
        pesel = new SimpleLongProperty();
        idSeriesNumber = new SimpleStringProperty();
        hourlyWage = new SimpleFloatProperty();
        position = new SimpleStringProperty();
        dateEmployed = new SimpleStringProperty();

    }

    public Employee(SimpleStringProperty name, SimpleStringProperty surname, SimpleLongProperty pesel, SimpleStringProperty idSeriesNumber, SimpleStringProperty dateEmployed, SimpleFloatProperty hourlyWage) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.idSeriesNumber = idSeriesNumber;
        this.dateEmployed = dateEmployed;
        this.hourlyWage = hourlyWage;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public long getPesel() {
        return pesel.get();
    }

    public SimpleLongProperty peselProperty() {
        return pesel;
    }

    public void setPesel(long pesel) {
        this.pesel.set(pesel);
    }

    public String getIdSeriesNumber() {
        return idSeriesNumber.get();
    }

    public SimpleStringProperty idSeriesNumberProperty() {
        return idSeriesNumber;
    }

    public void setIdSeriesNumber(String idSeriesNumber) {
        this.idSeriesNumber.set(idSeriesNumber);
    }

    public String getDateEmployed() {
        return dateEmployed.get();
    }

    public SimpleStringProperty dateEmployedProperty() {
        return dateEmployed;
    }

    public void setDateEmployed(String dateEmployed) {
        this.dateEmployed.set(dateEmployed);
    }

    public float getHourlyWage() {
        return hourlyWage.get();
    }

    public SimpleFloatProperty hourlyWageProperty() {
        return hourlyWage;
    }

    public void setHourlyWage(float hourlyWage) {
        this.hourlyWage.set(hourlyWage);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
}
}
