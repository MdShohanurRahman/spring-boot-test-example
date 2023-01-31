package com.example.springboottestexample.service.impl;

import com.example.springboottestexample.entity.Employee;
import com.example.springboottestexample.exception.ResourceNotFoundException;
import com.example.springboottestexample.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @BeforeAll
    static void beforeAll() {
    }

    @AfterAll
    static void afterAll() {
    }

    @Test
    @DisplayName("testEmployeeShouldBeSaved")
    void testSaveEmployee() {
        // given
        Employee employee = new Employee(
                1L,
                "John",
                "Doe",
                "john.doe@example.com"
        );

        // when
        when(employeeRepository.findByEmail(employee.getEmail())).thenReturn(Optional.empty());
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee result = employeeService.saveEmployee(employee);

        // then
        verify(employeeRepository, times(1)).findByEmail(employee.getEmail());
        verify(employeeRepository, times(1)).save(employee);
        assertThat(result).isEqualTo(employee);
    }

    @Test
    @DisplayName("testSaveEmployeeShouldThrowExceptionWhenEmailAlreadyExists")
    void testSaveEmployeeAlreadyExist() {
        // given
        Employee employee = new Employee(
                1L,
                "John",
                "Doe",
                "john.doe@example.com"
        );

        // when
        when(employeeRepository.findByEmail(employee.getEmail())).thenReturn(Optional.of(employee));
        assertThatThrownBy(() -> employeeService.saveEmployee(employee))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageStartingWith("Employee already exist with given email");

        // then
        verify(employeeRepository, times(1)).findByEmail(employee.getEmail());
        verify(employeeRepository, times(0)).save(employee);
    }


    @Test
    @DisplayName("testGetAllEmployeesShouldReturnListOfEmployees")
    public void testGetAllEmployees() {
        // given
        List<Employee> employeeList = Arrays.asList(
                new Employee(1L, "John", "Doe", "john.doe@example.com"),
                new Employee(2L, "Jane", "Doe", "jane.doe@example.com")
        );

        // when
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> result = employeeService.getAllEmployees();

        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat("John").isEqualTo(result.get(0).getFirstName());
        assertThat("Jane").isEqualTo(result.get(1).getFirstName());
    }

    @Test
    @DisplayName("testGetEmployeeByIdShouldReturnEmployee")
    void testGetEmployeeById() {
        // given
        Employee employee = new Employee(
                1L,
                "John",
                "Doe",
                "john.doe@example.com"
        );

        // when
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // then
        Optional<Employee> result = employeeService.getEmployeeById(1L);
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("testUpdateEmployeeShouldUpdateData")
    public void testUpdateEmployee() {
        // Given
        Employee employee = new Employee(
                1L,
                "John",
                "Doe",
                "john.doe@example.com"
        );

        // When
        when(employeeRepository.save(employee)).thenReturn(employee);
        employee.setFirstName("Jane");
        Employee result = employeeService.updateEmployee(employee);

        // Then
        verify(employeeRepository, times(1)).save(employee);
        assertThat(result).isEqualTo(employee);
    }

    @Test
    @DisplayName("testDeleteEmployeeShouldDeleteEmployee")
    public void testDeleteEmployee() {
        // Given
        Long id = 1L;

        // When
        employeeService.deleteEmployee(id);

        // Then
        verify(employeeRepository, times(1)).deleteById(id);
    }
}