package com.booleanuk.api.core.repository;

import com.booleanuk.api.core.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
