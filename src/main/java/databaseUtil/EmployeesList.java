package databaseUtil;

import java.util.List;

import databaseModel.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeesList {

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    private static EmployeesList ourInstance = new EmployeesList();


    private EmployeesList(){

    };

    public static EmployeesList getInstance() {
        return ourInstance;
    }
    public ObservableList<Employee> getEmployeeList() {
        return employeeList;
    }
    public void addEmployeeToList(Employee employee){
        employeeList.add(employee);
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = FXCollections.observableArrayList(employeeList);
    }
}
