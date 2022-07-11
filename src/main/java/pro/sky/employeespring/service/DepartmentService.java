package pro.sky.employeespring.service;

import pro.sky.employeespring.domain.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {

    Employee findMinOrMaxOfDep(Integer departmentId, Boolean direct);

    List<Employee> printEmpOrdDep(Integer departmentId);

    Map<Integer, List<Employee>> printEmpAll();
}
