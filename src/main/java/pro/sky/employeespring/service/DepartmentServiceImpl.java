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
    public Optional<Employee> findMinOrMaxOfDep(String departmentId, String direct) {
        List <Employee> employeeOfDep = rebuildOfDep(Integer.parseInt(departmentId));
        Optional<Employee> valEmp = null;
        switch (direct) {
            case "max":
                valEmp = Optional.of(employeeOfDep.stream().max(Comparator.comparing(Employee::getSalary)).get());
                break;
            case "min":
                valEmp = Optional.of(employeeOfDep.stream().min(Comparator.comparing(Employee::getSalary)).get());
                break;
        }

        return valEmp;
    }

    @Override
    public List<Employee> printEmpOrdDep(String departmentId) {
        return rebuildOfDep(Integer.parseInt(departmentId));
    }

    @Override
    public List<Employee> printEmpAll() {
        Map<String, Employee> employees = employeeService.getEmployeesMap();
        List<Employee> employeeList = new ArrayList<>(employees.values());
        return employeeList.stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
    }

}
