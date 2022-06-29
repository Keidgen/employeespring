package pro.sky.employeespring.service;

import org.springframework.stereotype.Service;
import pro.sky.employeespring.domain.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private List<Employee> rebuildOfDep(int departmentId) {
        Map<String, Employee> employees = employeeService.getEmployeesMap();
        List<Employee> employeeList = new ArrayList<>(employees.values());

        return employeeList.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Employee findMinOrMaxOfDep(Integer departmentId, Boolean direct) {
        List <Employee> employeeOfDep = rebuildOfDep(departmentId);
        Employee valEmp = null;
        if (direct){
            valEmp = employeeOfDep.stream().max(Comparator.comparing(Employee::getSalary)).get();
        } else valEmp = employeeOfDep.stream().min(Comparator.comparing(Employee::getSalary)).get();

        return valEmp;
    }

    @Override
    public List<Employee> printEmpOrdDep(Integer departmentId) {
        return rebuildOfDep(departmentId);
    }

    @Override
    public Map<Integer, List<Employee>> printEmpAll() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee :: getDepartment));
    }

}
