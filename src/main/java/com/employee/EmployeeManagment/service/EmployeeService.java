package com.employee.EmployeeManagment.service;

import com.employee.EmployeeManagment.model.Employee;
import com.employee.EmployeeManagment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;
    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<Employee>();
        repository.findAll().forEach(employees::add);
        return employees;
    }

    public Optional<Employee> findById(Long id){
        Optional<Employee> employeeData = repository.findById(id);
        return employeeData;
    }

    public Optional<Employee> findByFirstName(String name){
        Optional<Employee> employeeData = repository.findByFirstName(name);
        return employeeData;

    }

    public Employee updateEmployee(Long id, Employee employeeDetail){
        Optional<Employee> employeeData = repository.findById(id);
        if(employeeData.isPresent()){
            Employee employee = employeeData.get();
            employee.setFirstName(employeeDetail.getFirstName());
            employee.setLastName(employeeDetail.getLastName());
            employee.setBirthdate(employeeDetail.getBirthdate());
            employee.setGender(employeeDetail.getGender());
            employee.setSalary(employeeDetail.getSalary());
            return repository.save(employee);
        }
        else {
            return null;
        }
    }

    public ResponseEntity<HttpStatus> deleteEmployee(long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
