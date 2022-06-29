package pro.sky.employeespring.service;

import pro.sky.employeespring.domain.Employee;

import java.util.Map;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, String salary, String departmentId);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    String getEmployees();

    Map getEmployeesMap();
}
