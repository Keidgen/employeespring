package pro.sky.employeespring.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.employeespring.domain.Employee;
import pro.sky.employeespring.exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeespring.exceptions.EmployeeBadRequestException;
import pro.sky.employeespring.exceptions.EmployeeNotFoundException;
import pro.sky.employeespring.exceptions.EmployeeStorageIsFullException;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int maxEmployee = 30;
    private int key = 10;

    public final Map<String, Employee> employees = new HashMap<>(Map.of("1", new Employee("Гарри", "Поттер", 100.0, 1),
                                                           "2", new Employee("Гермиона" ,"Грейнджер", 90.0, 1),
                                                           "3", new Employee("Рон", "Уизли", 80.0, 1),
                                                           "4", new Employee("Драко", "Малфой", 110.0, 2),
                                                            "5", new Employee("Чжоу", "Чанг", 75.0, 3),
                                                            "6", new Employee("Седрик", "Диггори", 80.0, 3),
                                                            "7", new Employee("Северус", "Снегг", 150.0, 4),
                                                            "8", new Employee("Полумна", "Лавгуд", 75.0, 3),
                                                            "9", new Employee("Захария", "Смит", 65.0, 5),
                                                            "10", new Employee("Альбус", "Дамблдор", 200.0,4)
                                                          ));

    @Override
    public Employee addEmployee(String firstName, String lastName, Double salary, Integer departmentId) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        if (employees.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException();
        }
        validateInput(firstName, lastName);
        key = key + 1;
  
        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        if (!employees.containsValue(employee)) {
            employees.put(String.valueOf(key), employee);
        } else throw new EmployeeAlreadyAddedException();

        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = employees.values().stream()
                        .filter(emp -> emp.getFirstName().equals(firstName) && emp.getLastName().equals(lastName))
                        .findFirst()
                        .orElseThrow(EmployeeNotFoundException::new);
        employees.values().remove(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        return employees.values().stream()
                .filter(emp -> emp.getFirstName().equals(firstName) && emp.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
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


    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))){
            throw new EmployeeBadRequestException();
        }
    }

    @Override
    public Map getEmployeesMap() {
        return employees;
    }

    @Override
    public List<Employee> getAll(){
        return new ArrayList<>(employees.values());
    }

}
