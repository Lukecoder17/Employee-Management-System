package com.ab.springboot.controller;

import com.ab.springboot.exception.ResourceNotFoundException;
import com.ab.springboot.model.Employee;
import com.ab.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // Build create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Build get employee by ID REST API
    @GetMapping("{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with " + employeeId));
        return ResponseEntity.ok(employee);
    }

    // Build Update Employee REST API
    @PutMapping("{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long employeeId, @RequestBody Employee employeeDetails){
        Employee updateEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with " + employeeId));
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    // Build Delete Employee REST API
    @DeleteMapping("{employeeId}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with " + employeeId));
        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
