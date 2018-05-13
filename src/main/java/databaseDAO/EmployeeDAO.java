package databaseDAO;

import databaseModel.Employee;
import javafx.collections.ObservableList;

public interface EmployeeDAO {
	ObservableList<Employee> getAllAmployee();
	
	boolean checkPesel(Long pesel);
	boolean insertEmployee(Long pesel, String firstName, String secondName, String dateEmployed, Float hourlyWage, String phoneNumber, String jobPost);
	boolean deleteEmployee(Long employee);
}
