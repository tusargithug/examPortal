package com.examportal.examPortal.Controller;

import com.examportal.examPortal.Model.Employee;
import com.examportal.examPortal.Repository.EmployeeCustomRepo;
import com.examportal.examPortal.Repository.EmployeeRepo;
import com.examportal.examPortal.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeCustomRepo employeeCustomRepo;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Employee saveEmployee(@RequestBody Employee employee){

        return employeeService.saveEmployee(employee);
    }


    @GetMapping("/get")
  //  @PreAuthorize("hasAnyAuthority('USER')")
    public List<Employee> searchUsersByCriteria(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) Double salary) {
        return employeeCustomRepo.findEmployeeByCriteria(name,salary);
    }
}
