package pro.sky.employeespring.service;

import org.assertj.core.description.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.employeespring.domain.Employee;
import pro.sky.employeespring.exceptions.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplParameterTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Вениамин", "Орлов", 50.0, 3),
                new Employee("Владимир", "Скворцов", 80.0, 3),
                new Employee("Виктор", "Чайкин", 70.0, 4),
                new Employee("Валерий", "Сорокин", 90.0, 1),
                new Employee("Виталий", "Синицын", 30.0, 2)
        );
        when(employeeService.getAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalary")
    public void employeeWithMaxSalary(int departmentId, Employee expected) {
        assertThat(departmentService.findMaxOfDep(departmentId)).isEqualTo(expected);
    }

    @Test
    public void employeeWithMaxSalaryNeg() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findMaxOfDep(6));
    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalary")
    public void employeeWithMinSalary(int departmentId, Employee expected) {
        assertThat(departmentService.findMinOfDep(departmentId)).isEqualTo(expected);
    }

    @Test
    public void employeeWithMinSalaryNeg() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findMinOfDep(6));
    }

    @ParameterizedTest
    @MethodSource("employeesFromDepartmentParams")
    public void employeeFromDep(int departmentId, List<Employee> expected) {
        assertThat(departmentService.printEmpOfDep(departmentId)).asList().containsExactlyElementsOf(expected);
    }

    @Test
    public void employeesGroupDepartTest() {
        assertThat(departmentService.printEmpAll().values().stream().collect(Collectors.toList()).containsAll(List.of(
                new Employee("Вениамин", "Орлов", 50.0, 3),
                new Employee("Владимир", "Скворцов", 80.0, 3),
                new Employee("Виктор", "Чайкин", 70.0, 4),
                new Employee("Валерий", "Сорокин", 90.0, 1),
                new Employee("Виталий", "Синицын", 30.0, 2)
        )));
    }

    public static Stream<Arguments> employeeWithMaxSalary() {
        return Stream.of(
                Arguments.of(1, new Employee("Валерий", "Сорокин", 90.0, 1)),
                Arguments.of(3, new Employee("Владимир", "Скворцов", 80.0, 3))

        );
    }

    public static Stream<Arguments> employeeWithMinSalary() {
        return Stream.of(
                Arguments.of(2, new Employee("Виталий", "Синицын", 30.0, 2)),
                Arguments.of(3, new Employee("Вениамин", "Орлов", 50.0, 3))

        );
    }

    public static Stream<Arguments> employeesFromDepartmentParams() {
        return Stream.of(
                Arguments.of(2, List.of(new Employee("Виталий", "Синицын", 30.0, 2))),
                Arguments.of(3, List.of(new Employee("Вениамин", "Орлов", 50.0, 3),
                         new Employee("Владимир", "Скворцов", 80.0, 3))),
                Arguments.of(6, Collections.emptyList())

        );
    }

}
