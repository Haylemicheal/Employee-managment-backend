package com.employee.EmployeeManagment.controller;

import com.employee.EmployeeManagment.model.Employee;
import com.employee.EmployeeManagment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class controller {
    @Autowired
    EmployeeRepository repository;

    @CrossOrigin
    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee){
        return repository.save(employee);
    }

    @CrossOrigin
    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> findAll() {
        try {
            List<Employee> employees = new ArrayList<Employee>();
            repository.findAll().forEach(employees::add);
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
        Optional<Employee> employeeData = repository.findById(id);
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
        Optional<Employee> employeeData = repository.findByFirstName(name);
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
        Optional<Employee> employeeData = repository.findById(id);
        if(employeeData.isPresent()){
            Employee employee = employeeData.get();
            employee.setFirstName(employeeDetail.getFirstName());
            employee.setLastName(employeeDetail.getLastName());
            employee.setBirthdate(employeeDetail.getBirthdate());
            employee.setGender(employeeDetail.getGender());
            employee.setSalary(employeeDetail.getSalary());
            return new ResponseEntity<>(repository.save(employee), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
