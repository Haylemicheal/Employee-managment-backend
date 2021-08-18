package com.employee.EmployeeManagment.controller;

import com.employee.EmployeeManagment.model.Employee;
import com.employee.EmployeeManagment.repository.EmployeeRepository;
import com.employee.EmployeeManagment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @CrossOrigin
    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @CrossOrigin
    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> findAll() {
        try {
            List<Employee> employees= employeeService.findAll();
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @CrossOrigin
    @RequestMapping("/employee/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id){
       Optional<Employee> employeeData = employeeService.findById(id);
        if(employeeData.isPresent()){
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping("/employee/search/{name}")
    public ResponseEntity<Employee> findByFirstName(@PathVariable String name){
        Optional<Employee> employeeData = employeeService.findByFirstName(name);
        if(employeeData.isPresent()){
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id, @RequestBody Employee employeeDetail){
        Employee employeeData = employeeService.updateEmployee(id,employeeDetail);
        if(employeeData!=null){
            return new ResponseEntity<>(employeeData, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
        return employeeService.deleteEmployee(id);
    }

    @CrossOrigin
    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        return employeeService.deleteAllEmployees();
    }

}
