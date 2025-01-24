package com.booleanuk.api.employees;

import org.springframework.data.jpa.repository.JpaRepository;

//Integer because the PK is an int
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}
