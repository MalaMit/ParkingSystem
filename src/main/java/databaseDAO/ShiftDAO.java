package databaseDAO;

import databaseModel.Shift;
import javafx.collections.ObservableList;

public interface ShiftDAO {
	ObservableList<Shift> getAllForEmployee(String pesel);
	
	boolean insertNewWorkTime(String shiftStart, String shiftEnd, Long pesel, String shiftType);
}
