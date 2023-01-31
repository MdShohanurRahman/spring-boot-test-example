/**
 * Created By shoh@n
 * Date: 1/29/2023
 */

package com.example.springboottestexample.service;

import com.example.springboottestexample.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(Long id);

    Employee updateEmployee(Employee updatedEmployee);

    void deleteEmployee(Long id);
}
