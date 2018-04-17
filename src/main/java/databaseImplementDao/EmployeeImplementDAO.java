package databaseImplementDao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import databaseModel.Employee;
import databaseModel.EmployeeType;
import databaseUtil.DBUtil;
import databaseDAO.EmployeeDAO;



public class EmployeeImplementDAO implements EmployeeDAO {
	
	@Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = null;
        ResultSet resultSet = null;
        Connection connection = DBUtil.dbConnect();
        String getAllEmployeesSQL = "SELECT * FROM employee;";
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet = statement.executeQuery(getAllEmployeesSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        employeeList = resultSetToList(resultSet);
        DBUtil.dbDisconnect();
        return employeeList;
    }

    @Override

    public void addEmployee(Long pesel, String fname, String surname, String idseriesnumber, Date datemployed, Float hourlywage, EmployeeType position) {
        System.out.println(position);
        PreparedStatement addEmployeePS = null;
        Connection connection = DBUtil.dbConnect();
        String insertEmployeeSQL = "INSERT INTO employee(pesel,fname,surname,idseriesnumber,dateemployed,hourlywage,jobpos)"+
                " VALUES(?,?,?,?,?,?,?);";
        try {
            addEmployeePS = connection.prepareStatement(insertEmployeeSQL);
            addEmployeePS.setBigDecimal(1, BigDecimal.valueOf(pesel));
            addEmployeePS.setString(2,fname);
            addEmployeePS.setString(3,surname);
            addEmployeePS.setString(4,idseriesnumber);
            addEmployeePS.setDate(5,datemployed);
            addEmployeePS.setFloat(6,hourlywage);
            switch(position){
                case CASHIER: {addEmployeePS.setString(7,"cashier");break;}
                case GUARD: {addEmployeePS.setString(7,"guard");break;}
                case MANAGER: {addEmployeePS.setString(7,"manager");break;}
                case REGULAR: {addEmployeePS.setString(7,"regular");break;}

            }
            addEmployeePS.execute();
        } catch (SQLException e) {
            e.printStackTrace();//TODO handle exception to GUI
        }


    }

    @Override
    public Employee getEmployeeByPesel(String pesel) {
        return null;
    }

    @Override
    public Employee getEmployeeByFirstName() {
        return null;
    }

    @Override
    public Employee getEmployeeBySurname() {
        return null;
    }

    @Override
    public Employee getEmployeeByDateOfBirth(OrderBy orderBy) {
        return null;
    }

    private List<Employee> resultSetToList(ResultSet resultSet){
        Employee employee;
        List<Employee> employeeList = new ArrayList<>();
        try {
            while(resultSet.next()){
                employee = new Employee();
            	employee.setPosition(resultSet.getString("jobpos"));
                employee.setName(resultSet.getString("fname"));
                employee.setSurname(resultSet.getString("surname"));
                employee.setPesel(resultSet.getLong("pesel"));
                employee.setIdSeriesNumber(resultSet.getString("idseriesnumber"));
                employee.setDateEmployed(resultSet.getDate("dateemployed").toString());
                employee.setHourlyWage(resultSet.getFloat("hourlywage"));
                employee.setPosition(resultSet.getString("jobpos"));
                employeeList.add(employee);
                System.out.println(employee.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return employeeList;
    }

}
