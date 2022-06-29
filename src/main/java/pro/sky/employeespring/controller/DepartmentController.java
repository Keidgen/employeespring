package pro.sky.employeespring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeespring.domain.Employee;
import pro.sky.employeespring.service.DepartmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping("/max-salary")
    public Optional<Employee> findEmpMaxSalaryOfDep(@RequestParam String departmentId) {
        return departmentService.findMinOrMaxOfDep(departmentId, "max");
    }

    @RequestMapping("/min-salary")
    public Optional<Employee> findEmpMinSalaryOfDep(@RequestParam String departmentId) {
        return departmentService.findMinOrMaxOfDep(departmentId, "min");
    }

    @RequestMapping(path = "/all")
    public List<Employee> printEmpAll() {
        return departmentService.printEmpAll();
    }

    @RequestMapping(path = "/all", params = {"departmentId"})
    public List<Employee> printEmpOrdDep(@RequestParam String departmentId) {
        return departmentService.printEmpOrdDep(departmentId);
    }


}
