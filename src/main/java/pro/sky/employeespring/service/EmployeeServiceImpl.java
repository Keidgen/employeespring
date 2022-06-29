package pro.sky.employeespring.service;

import org.springframework.stereotype.Service;
import pro.sky.employeespring.domain.Employee;
import pro.sky.employeespring.exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeespring.exceptions.EmployeeNotFoundException;
import pro.sky.employeespring.exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int maxEmployee = 30;
    private int key = 10;

    public Map<String, Employee> employees = new HashMap<>(Map.of("1", new Employee("Гарри", "Поттер", "100", "1"),
                                                           "2", new Employee("Гермиона" ,"Грейнджер", "90", "1"),
                                                           "3", new Employee("Рон", "Уизли", "80", "1"),
                                                           "4", new Employee("Драко", "Малфой", "110", "2"),
                                                            "5", new Employee("Чжоу", "Чанг", "75", "3"),
                                                            "6", new Employee("Седрик", "Диггори", "80", "3"),
                                                            "7", new Employee("Северус", "Снегг", "150", "4"),
                                                            "8", new Employee("Полумна", "Лавгуд", "75", "3"),
                                                            "9", new Employee("Захария", "Смит", "65", "5"),
                                                            "10", new Employee("Альбус", "Дамблдор", "200","4")
                                                          ));

    @Override
    public Employee addEmployee(String firstName, String lastName, String salary, String departmentId) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        key = key + 1;
        if (employees.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        if (!employees.containsValue(employee)) {
            employees.put(String.valueOf(key), employee);
        } else throw new EmployeeAlreadyAddedException();

        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName, null, null);

        if (employees.containsValue(employee)){
            employees.values().remove(employee);
            return employee;
        }

        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName, null, null);

        if (employees.containsValue(employee)){
            return employee;
        }

        throw new EmployeeNotFoundException();
    }


    @Override
    public String getEmployees() {
        String allMap = "";
        Set keys = employees.keySet();
        for(Object key: keys){
            allMap = allMap + key + ": " + employees.get(key) + " ";
        }
        return allMap;
    }

    @Override
    public Map getEmployeesMap() {
        return employees;
    }

}
