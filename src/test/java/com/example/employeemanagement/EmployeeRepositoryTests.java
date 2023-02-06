package com.example.employeemanagement;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit Test for Saving An Employee
    @Test
    @Order(1)
    public void saveEmployeeTest(){

        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Rada")
                .email("testing@email.com")
                .build();

        employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    // JUnit Test for Getting An Employee Details
    @Test
    @Order(2)
    public void getEmployeeTest(){

        Employee employee = employeeRepository.findById(1L).get();

        Assertions.assertThat(employee.getId()).isEqualTo(1L);
    }

    // JUnit Test for Getting All the Employees Details
    @Test
    @Order(3)
    public void getAllEmployeesTest(){

        List<Employee> employees = employeeRepository.findAll();

        Assertions.assertThat(employees.size()).isGreaterThan(0);
    }

    // JUnit Test for Update An Employee
    @Test
    @Order(4)
    public void updateEmployeeTest(){

        Employee employee = employeeRepository.findById(1L).get();

        employee.setFirstName("Johnny");
        employee.setLastName("Phantom");
        employee.setEmail("john@gmail.com");

        Employee employeeUpdated = employeeRepository.save(employee);

        Assertions.assertThat(employeeUpdated.getFirstName()).isEqualTo("Johnny");
        Assertions.assertThat(employeeUpdated.getLastName()).isEqualTo("Phantom");
        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("john@gmail.com");
    }

    // JUnit Test for Deleting An Employee
    @Test
    @Order(5)
    public void deleteEmployeeTest(){

        Employee employee = employeeRepository.findById(1L).get();

        employeeRepository.deleteById(employee.getId());

        Employee employee1 = null;
        Optional<Employee> optionalEmployee = employeeRepository.findById(1L);

        if(optionalEmployee.isPresent()){
            employee1 = optionalEmployee.get();
        }

        Assertions.assertThat(employee1).isNull();

    }
}
