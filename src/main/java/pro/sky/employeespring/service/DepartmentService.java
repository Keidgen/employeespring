package pro.sky.employeespring.service;

import pro.sky.employeespring.domain.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {

    Employee findMaxOfDep(Integer departmentId);
    Employee findMinOfDep(Integer departmentId);

    List<Employee> printEmpOfDep(Integer departmentId);

    Map<Integer, List<Employee>> printEmpAll();
}
