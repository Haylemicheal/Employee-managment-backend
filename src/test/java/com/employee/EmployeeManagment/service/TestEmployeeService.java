package com.employee.EmployeeManagment.service;

import com.employee.EmployeeManagment.model.Employee;
import com.employee.EmployeeManagment.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TestEmployeeService {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void verify_create() throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Employee emp1 = new Employee("Abebe", "Kebede", ft.parse("1996-03-29T13:34:00.000"), "M", 2000);
        when(repository.save(emp1)).thenReturn(emp1);
        Employee employee = employeeService.createEmployee(emp1);
        assertEquals(emp1, employee);
    }

    @Test
    public void verify_findall() throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Employee emp1 = new Employee("Abebe", "Kebede", ft.parse("1996-03-29T13:34:00.000"), "M", 2000);
        Employee emp2 = new Employee("Abebech", "Kebedech", ft.parse("1994-03-29T13:34:00.000"), "M", 2000);
        List<Employee> list = new ArrayList<>();
        list.add(emp1);
        list.add(emp2);
        when(repository.findAll()).thenReturn(list);
        List<Employee> employees = employeeService.findAll();
        assertEquals(2, employees.size());
    }

    @Test
    public void verify_findbyid() throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Employee emp1 = new Employee("Abebe", "Kebede", ft.parse("1996-03-29T13:34:00.000"), "M", 2000);
        when(repository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(emp1));
        Optional<Employee> employee = employeeService.findById(Long.valueOf(1));
        assertEquals("Abebe", employee.get().getFirstName());
        assertEquals("Kebede", employee.get().getLastName());
        assertEquals(ft.parse("1996-03-29T13:34:00.000"), employee.get().getBirthdate());
    }

    @Test
    public void verify_findbyfirstname() throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Employee emp1 = new Employee("Abebe", "Kebede", ft.parse("1996-03-29T13:34:00.000"), "M", 2000);
        when(repository.findByFirstName("Abebe")).thenReturn(java.util.Optional.of(emp1));
        Optional<Employee> employee = employeeService.findByFirstName("Abebe");
        assertEquals("Abebe", employee.get().getFirstName());
        assertEquals("Kebede", employee.get().getLastName());
        assertEquals(ft.parse("1996-03-29T13:34:00.000"), employee.get().getBirthdate());
    }

    @Test
    public void verify_updateEmployee() throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Employee emp1 = new Employee("Abebe", "Kebede", ft.parse("1996-03-29T13:34:00.000"), "M", 2000);
        Employee emp2 = new Employee("Abebe", "Kebede", ft.parse("1996-03-29T13:34:00.000"), "M", 3000);
        when(repository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(emp1));
        when(repository.save(emp1)).thenReturn(emp2);
        Employee employee = employeeService.updateEmployee(Long.valueOf(1), emp1);
        assertEquals(emp2, employee);
    }
   @Test
    public void verify_deletebyid() {
        employeeService.deleteEmployee(Long.valueOf(1));
        verify(repository).deleteById(Long.valueOf(1));
   }
    @Test
    public void verify_deleteall() {
        employeeService.deleteAllEmployees();
        verify(repository).deleteAll();
    }
}
