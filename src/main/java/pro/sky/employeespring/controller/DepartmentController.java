package pro.sky.employeespring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeespring.domain.Employee;
import pro.sky.employeespring.service.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping("/max-salary")
    public Employee findEmpMaxSalaryOfDep(@RequestParam Integer departmentId) {
        return departmentService.findMaxOfDep(departmentId);
    }

    @RequestMapping("/min-salary")
    public Employee findEmpMinSalaryOfDep(@RequestParam Integer departmentId) {
        return departmentService.findMinOfDep(departmentId);
    }

    @RequestMapping(path = "/all")
    public Map<Integer, List<Employee>> printEmpAll() {
        return departmentService.printEmpAll();
    }

    @RequestMapping(path = "/all", params = {"departmentId"})
    public List<Employee> printEmpOrdDep(@RequestParam Integer departmentId) {
        return departmentService.printEmpOfDep(departmentId);
    }


}
