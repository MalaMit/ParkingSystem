package databaseDAO;

import java.sql.Date;
import java.util.List;

import databaseImplementDao.OrderBy;
import databaseModel.Employee;
import databaseModel.EmployeeType;

public interface EmployeeDAO {
	List<Employee> getAllEmployees();
    void addEmployee(Long pesel, String fname, String surname, String idseriesnumber, Date datemployed, Float hourlywage, EmployeeType position);
    Employee getEmployeeByPesel(String pesel);
    Employee getEmployeeByFirstName();
    Employee getEmployeeBySurname();
    Employee getEmployeeByDateOfBirth(OrderBy orderBy);
}
