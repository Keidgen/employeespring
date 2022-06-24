package pro.sky.employeespring.service;

import pro.sky.employeespring.domain.Employee;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    String getEmployees();
}
