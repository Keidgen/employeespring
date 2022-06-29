package pro.sky.employeespring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeespring.domain.Employee;
import pro.sky.employeespring.exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeespring.exceptions.EmployeeNotFoundException;
import pro.sky.employeespring.exceptions.EmployeeStorageIsFullException;
import pro.sky.employeespring.service.EmployeeService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String salary, @RequestParam String departmentId) {
        try {
            return employeeService.addEmployee(firstName, lastName, salary, departmentId);
        } catch (EmployeeStorageIsFullException e) {
            throw new EmployeeStorageIsFullException("Массив сотрудников заполнен");
        } catch (EmployeeAlreadyAddedException e) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен в массив");
        }
    }

    @RequestMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            return employeeService.deleteEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            throw new EmployeeNotFoundException("Такой сотрудник отсутствует");
        }
    }

    @RequestMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            return employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            throw new EmployeeNotFoundException("Такой сотрудник отсутствует");
        }
    }

    @RequestMapping("/printall")
    public String printAllEmployee() {
        return employeeService.getEmployees();
    }
}
