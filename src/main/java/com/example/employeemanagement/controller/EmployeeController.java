package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    // GET ALL EMPLOYEES
    @GetMapping("get-all-employees.html")
    public String getAllEmployees(Model model) {
        model.addAttribute("getAllEmployees", employeeService.getAllEmployees());
        return "get-all-employees";
    }

    @GetMapping("/showAddEmployeeForm")
    public String showAddEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add-employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showUpdateEmployeeForm/{id}")
    public String showUpdateEmployeeForm(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update-employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

}
