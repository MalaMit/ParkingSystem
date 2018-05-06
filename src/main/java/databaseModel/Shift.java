package databaseModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Shift {

	private IntegerProperty shiftID;
	private StringProperty shiftStart;
	private StringProperty shiftEnd;
	private LongProperty pesel;
	private StringProperty shiftType;
	
	public Shift() {
		this.shiftID = new SimpleIntegerProperty();
		this.shiftStart = new SimpleStringProperty();
		this.shiftEnd = new SimpleStringProperty();
		this.pesel = new SimpleLongProperty();
		this.shiftType = new SimpleStringProperty();
	}

	/**
	 * shiftID
	 */
	public void setShiftID(Integer shiftID) {
		this.shiftID.set(shiftID);
	}
	
	public int getShiftID() {
		return shiftID.get();
	}
	
	public IntegerProperty getShiftIDProperty() {
		return shiftID;
	}

	/**
	 * shiftStart
	 */
	public void setShiftStart(String shiftStart) {
		this.shiftStart.set(shiftStart);
	}
	
	public String getShiftStart() {
		return shiftStart.get();
	}
	
	public StringProperty getShiftStartProperty() {
		return shiftStart;
	}

	/**
	 * shiftEnd
	 */
	public void setShiftEnd(String shiftEnd) {
		this.shiftEnd.set(shiftEnd);
	}
	
	public String getShiftEnd() {
		return shiftEnd.get();
	}
	
	public StringProperty getShiftEndProperty() {
		return shiftEnd;
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
	 * shiftType
	 */
	public void setShiftType(String shiftType) {
		this.shiftType.set(shiftType);
	}
	
	public String getShiftType() {
		return shiftType.get();
	}
	
	public StringProperty getShiftTypeProperty() {
		return shiftType;
	}
}
