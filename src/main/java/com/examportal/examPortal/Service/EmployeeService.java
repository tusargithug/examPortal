package com.examportal.examPortal.Service;

import com.examportal.examPortal.Model.Employee;
import com.examportal.examPortal.Repository.EmployeeCustomRepo;
import com.examportal.examPortal.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeCustomRepo employeeCustomRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee saveEmployee(Employee employee){

        return employeeRepo.save(employee);
    }
    public List<Employee> searchEmployeeByNameAndSalary(String name, Double salary) {
        return employeeCustomRepo.findEmployeeByCriteria(name,salary);
    }
}
