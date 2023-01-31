package com.example.springboottestexample.repository;

import com.example.springboottestexample.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testFindByEmailShouldReturnEmployee() {
        // given
        Employee john = new Employee(1L, "John", "Doe", "john.doe@example.com");
        employeeRepository.save(john);

        // when
        Optional<Employee> found = employeeRepository.findByEmail(john.getEmail());

        // then
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getFirstName()).isEqualTo(john.getFirstName());
        assertThat(found.get().getLastName()).isEqualTo(john.getLastName());
        assertThat(found.get().getEmail()).isEqualTo(john.getEmail());
    }

    @Test
    public void testFindByEmailShouldReturnEmpty() {
        // when
        Optional<Employee> found = employeeRepository.findByEmail("non-existing-email@example.com");

        // then
        assertThat(found.isPresent()).isFalse();
    }
}