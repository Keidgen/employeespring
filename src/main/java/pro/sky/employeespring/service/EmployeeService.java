package pro.sky.employeespring.service;

import pro.sky.employeespring.domain.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, Double salary, Integer departmentId);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    String getEmployees();

    Map getEmployeesMap();

    List<Employee> getAll();
}
