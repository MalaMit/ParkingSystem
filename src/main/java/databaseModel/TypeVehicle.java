package databaseModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TypeVehicle {
	private StringProperty type;
	private IntegerProperty priceForHour;

	public TypeVehicle() {
		this.type= new SimpleStringProperty();
		this.priceForHour = new SimpleIntegerProperty();
	}
	
	public String getType() {
		return type.get();
	}
	
	public void setType( String type ) {
		this.type.set(type);
	}
	
	public StringProperty getTypeProperty() {
		return type;
	}
	
	public int getPriceForHour() {
		return priceForHour.get();
	}
	
	public void setPriceForHour( int priceForHour ) {
		this.priceForHour.set(priceForHour);
	}
	
	public IntegerProperty getPriceForHourProperty() {
		return priceForHour;
	}  
	
	@Override
	public String toString() {
		return type.get();
	}
}
