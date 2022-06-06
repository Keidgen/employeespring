package pro.sky.employeespring.service;

import org.springframework.stereotype.Service;
import pro.sky.employeespring.domain.Employee;
import pro.sky.employeespring.exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeespring.exceptions.EmployeeNotFoundException;
import pro.sky.employeespring.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int maxEmployee = 30;
    private Employee[] emp = {new Employee("Гарри", "Поттер"),
                                               new Employee("Гермиона" ,"Грейнджер"),
                                               new Employee("Рон", "Уизли"),
                                               new Employee("Драко", "Малфой"),
                                               new Employee("Чжоу", "Чанг"),
                                               new Employee("Седрик", "Диггори"),
                                               new Employee("Северус", "Снегг"),
                                               new Employee("Полумна", "Лавгуд"),
                                               new Employee("Захария", "Смит"),
                                               new Employee("Альбус", "Дамблдор"),
                                               new Employee("Том", "Реддл"),
                                               new Employee("Златопуст", "Локонс"),
                                               new Employee("Джастин", "Финч-Флетчли")};
    List<Employee> empl = Arrays.asList(emp);
    List<Employee> employees = new ArrayList<>(empl);

    @Override
    public Employee addEmployee(String firstName, String lastName) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        if (employees.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException();
        }
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        Employee employee = new Employee(firstName, lastName);
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Iterator<Employee> employeeIterator = employees.iterator();
        while(employeeIterator.hasNext()) {
            Employee nextEmployee = employeeIterator.next();
            if (nextEmployee.getFirstName().equals(firstName) && nextEmployee.getLastName().equals(lastName)) {
                employeeIterator.remove();
                return nextEmployee;
            }
        }

        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        for (int i = 0; i < employees.size(); i++) {
            if ((employees.get(i).getFirstName().equals(firstName)) && (employees.get(i).getLastName().equals(lastName))) {
                return employees.get(i);
            }
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public List<Employee> getEmployees() {
        return employees;
    }
}
