package pro.sky.employeespring.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.employeespring.domain.Employee;
import pro.sky.employeespring.exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeespring.exceptions.EmployeeBadRequestException;
import pro.sky.employeespring.exceptions.EmployeeNotFoundException;
import pro.sky.employeespring.exceptions.EmployeeStorageIsFullException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;


public class EmployeeServiceImplParameterTest {
    private final EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    @ParameterizedTest
    @MethodSource("parameterForTesting")
    public void addTestAlreadyAdding(String firstName, String lastName, Double salary, Integer departmentId) {
        Employee expected = new Employee(firstName, lastName, salary, departmentId);
        assertThat(employeeServiceImpl.addEmployee(firstName, lastName, salary, departmentId)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(()->employeeServiceImpl.addEmployee(firstName, lastName, salary, departmentId));

        assertThatExceptionOfType(EmployeeBadRequestException.class)
                .isThrownBy(()->employeeServiceImpl.addEmployee("Petr#", "Ivanov", salary, departmentId));


    }

    @ParameterizedTest
    @MethodSource("parameterForTesting")
    public void addTestFullAdding(String firstName, String lastName, Double salary, Integer departmentId) {
        List<Employee> employeeList = generateEmployees(20);
        employeeList.forEach(employee ->
                assertThat(employeeServiceImpl.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment())).isEqualTo(employee));

        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(()->employeeServiceImpl.addEmployee(firstName, lastName, salary, departmentId));
    }

    @ParameterizedTest
    @MethodSource("parameterForTesting")
    public void removeTestNotFound(String firstName, String lastName, Double salary, Integer departmentId) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeServiceImpl.deleteEmployee("test", "test"));

        Employee expected = new Employee(firstName, lastName, salary, departmentId);
        assertThat(employeeServiceImpl.addEmployee(firstName, lastName, salary, departmentId)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeServiceImpl.deleteEmployee("test", "test"));
    }

    @ParameterizedTest
    @MethodSource("parameterForTesting")
    public void removeTesting(String firstName, String lastName, Double salary, Integer departmentId) {
        Employee expected = new Employee(firstName, lastName, salary, departmentId);
        assertThat(employeeServiceImpl.addEmployee(firstName, lastName, salary, departmentId)).isEqualTo(expected);

        assertThat(employeeServiceImpl.deleteEmployee(firstName, lastName)).isEqualTo(expected);

        assertThat(employeeServiceImpl.getAll()).asList().hasSize(10);
    }

    @ParameterizedTest
    @MethodSource("parameterForTesting")
    public void findTesting(String firstName, String lastName, Double salary, Integer departmentId) {
        Employee expected = new Employee(firstName, lastName, salary, departmentId);
        assertThat(employeeServiceImpl.addEmployee(firstName, lastName, salary, departmentId)).isEqualTo(expected);

        assertThat(employeeServiceImpl.findEmployee(firstName, lastName)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("parameterForTesting")
    public void getAllTesting(String firstName, String lastName, Double salary, Integer departmentId) {
        assertThat(employeeServiceImpl.getAll()).asList().hasSize(10);
    }

    private List<Employee> generateEmployees(int size) {
        return Stream.iterate(1, i -> i + 1)
                .limit(size)
                .map(i -> new Employee("firstName" + (char) ((int) 'a' + i), "lastName" + (char) ((int) 'a' + i), 50.0 + i, i))
                .collect(Collectors.toList());
    }

    private static Stream<Arguments> parameterForTesting() {
        return Stream.of(
                Arguments.of("Дональд", "Дак", 10.0, 2),
                Arguments.of("Иванов", "Иван", 10.0, 2),
                Arguments.of("Порри", "Гаттер", 10.0, 2),
                Arguments.of("Сен", "Аесли", 10.0, 2)
        );
    }
}
