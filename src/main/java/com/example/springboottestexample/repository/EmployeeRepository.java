/**
 * Created By shoh@n
 * Date: 1/29/2023
 */

package com.example.springboottestexample.repository;

import com.example.springboottestexample.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
}
