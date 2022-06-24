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

    Map<String, Employee> employees = new HashMap<>(Map.of("1", new Employee("Гарри", "Поттер"),
                                                           "2", new Employee("Гермиона" ,"Грейнджер"),
                                                           "3", new Employee("Рон", "Уизли"),
                                                           "4", new Employee("Драко", "Малфой"),
                                                            "5", new Employee("Чжоу", "Чанг"),
                                                            "6", new Employee("Седрик", "Диггори"),
                                                            "7", new Employee("Северус", "Снегг"),
                                                            "8", new Employee("Полумна", "Лавгуд"),
                                                            "9", new Employee("Захария", "Смит"),
                                                            "10", new Employee("Альбус", "Дамблдор")
                                                          ));
/*    private Employee[] emp = {new Employee("Гарри", "Поттер"),
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
    List<Employee> employees = new ArrayList<>(empl);*/

    @Override
    public Employee addEmployee(String firstName, String lastName) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        key = key + 1;
        if (employees.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsValue(employee)) {
            employees.put(String.valueOf(key), employee);
        } else throw new EmployeeAlreadyAddedException();
      /*  for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException();
            }
        }*/
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);

        if (employees.containsValue(employee)){
            employees.values().remove(employee);
          //  employees.remove(employee);
            return employee;
        }

        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);

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
}
