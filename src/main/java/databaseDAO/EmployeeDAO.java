package databaseDAO;

import java.util.List;

import databaseModel.Employee;
import databaseModel.EmployeeType;

public interface EmployeeDAO {
	List<Employee> getAllEmployees();
    void addEmployee(Long pesel, String fname, String surname, String idseriesnumber, Date datemployed, Float hourlywage, EmployeeType position);
    Employee getEmployeeByPesel(String pesel);
}
