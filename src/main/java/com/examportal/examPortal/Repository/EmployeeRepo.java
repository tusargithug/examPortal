package com.examportal.examPortal.Repository;

import com.examportal.examPortal.Model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EmployeeRepo extends JpaRepository<Employee, String> {
}
