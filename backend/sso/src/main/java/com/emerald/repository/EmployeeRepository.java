package com.emerald.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emerald.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByUserId(int userId);
    Employee deleteByUserId(int userId);
}
