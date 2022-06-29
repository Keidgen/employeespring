package pro.sky.employeespring.service;

import pro.sky.employeespring.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Optional<Employee> findMinOrMaxOfDep(String departmentId, String direct);

    List<Employee> printEmpOrdDep(String departmentId);

    List<Employee> printEmpAll();
}
