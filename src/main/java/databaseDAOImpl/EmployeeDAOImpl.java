package databaseDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseDAO.EmployeeDAO;
import databaseModel.Employee;
import databaseUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public ObservableList<Employee> getAllAmployee() {
		String sql ="SELECT * FROM employee";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ObservableList<Employee> employeeList = FXCollections.observableArrayList();
			while(rsSet.next()) {
				Employee employee = new Employee();
				employee.setName(rsSet.getString("firstName"));
				employee.setSurname(rsSet.getString("secondName"));
				employee.setPesel(rsSet.getLong("pesel"));
				employee.setPhone(rsSet.getString("phone"));
				employee.setDateEmployed(rsSet.getString("dateEmployed"));
				employee.setHourlyWage(rsSet.getFloat("hourlyWage"));
				employee.setPosition(rsSet.getString("jobPost"));
				employeeList.add(employee);
			}
			return employeeList;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertEmployee(Long pesel, String firstName, String secondName, String dateEmployed,
			Float hourlyWage, String phoneNumber, String jobPost) {
		String sql = "INSERT INTO employee (pesel, firstName, secondName, dateEmployed, hourlyWage, phone, jobPost)  VALUES ('"
				+ pesel + "', '" + firstName + "', '"+ secondName +"', '"+ dateEmployed +"', '"+ hourlyWage +"', '"+ phoneNumber +"', '"+ jobPost +"'); ";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteEmployee(Long employee) {
		String sql = "DELETE FROM employee WHERE pesel = '"+ employee +"'";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkPesel(Long pesel) {
		String sql = "SELECT pesel FROM employee WHERE pesel = '"+ pesel +"'";
		boolean isExist = false;
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			if (rsSet.next()) {
				isExist = true;
			}	
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isExist;
	}

}
