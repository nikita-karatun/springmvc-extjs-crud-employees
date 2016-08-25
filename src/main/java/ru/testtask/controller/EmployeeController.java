package ru.testtask.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ru.testtask.model.EmployeesResponse;
import ru.testtask.repository.EmployeeRepository;
import ru.testtask.vo.Employee;

@Controller
@RequestMapping("api")
public class EmployeeController {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "employee/save", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
    }

    @RequestMapping(value = "employee/loadEmployees")
    @ResponseBody
    public EmployeesResponse loadAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return new EmployeesResponse(employees);
    }

    @RequestMapping(value = "employee/loadEmployeesWithFilter")
    @ResponseBody
    public EmployeesResponse loadEmployeesWithFilter(@RequestParam("employee") String[] employeeStringArray) {
        Employee employee = Employee.composeFromStringArray(employeeStringArray);
        List<Employee> employees = employeeRepository.findAll(employee);
        return new EmployeesResponse(employees);
    }

    @RequestMapping(value = "employee/delete", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteEmployee(@RequestBody Employee employee) {
        employeeRepository.delete(employee);
    }

    @RequestMapping(value = "employee/updateEmployee", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateEmployees(@RequestBody Employee employee) {
        employeeRepository.save(employee);
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex) {
        logger.error(null, ex);
    }
}
