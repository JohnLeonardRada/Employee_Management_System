package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    public void addEmployee(Employee employee);
    public Employee getEmployeeById(long id);
    public void deleteEmployeeById(long id);
}
