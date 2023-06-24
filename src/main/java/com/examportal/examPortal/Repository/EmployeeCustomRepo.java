package com.examportal.examPortal.Repository;

import com.examportal.examPortal.Model.Employee;

import java.util.List;

public interface EmployeeCustomRepo {

        List<Employee> findEmployeeByCriteria(String name, Double salary);

}
