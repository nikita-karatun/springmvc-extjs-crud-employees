package ru.testtask.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.testtask.dao.EmployeeDao;
import ru.testtask.repository.EmployeeRepository;
import ru.testtask.utils.StringUtils;
import ru.testtask.vo.Employee;

@Controller
@RequestMapping("api")
public class EmployeeController {
    private Logger logger = Logger.getLogger(getClass());
    EmployeeDao employeeDao;

    @Autowired
    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "employee/save", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveEmployee(@RequestBody Employee employee) {
        System.out.println(employee);
        return employeeDao.addEmployee(employee);
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "employee/loadEmployees")
    @ResponseBody
    public Map<String, List<Employee>> loadAllEmployees() {
        Map<String, List<Employee>> employees = new HashMap<String, List<Employee>>();
        List<Employee> employees1 = employeeRepository.findAll();
        employees.put("employees", employees1);
        return employees;
    }

    @RequestMapping(value = "employee/loadEmployeesWithFilter")
    @ResponseBody
    public Map<String, List<Employee>> loadEmployeesWithFilter(
            @RequestParam("employee") String[] employeeStringArray) {
        /*
		 * по какой то причине ExtJs отправляет этот запрос в ISO-8859-1, не
		 * получилось исправть это на клиентской стороне, поэтому произвожу
		 * перекодировку тут
		 */
        employeeStringArray = StringUtils.correctEncoding(employeeStringArray);
        Employee employee = Employee.composeFromStringArray(employeeStringArray);

        Map<String, List<Employee>> employees = new HashMap<String, List<Employee>>();
        employees.put("employees", employeeDao.getEmployeesList(employee));
        return employees;
    }

    @RequestMapping(value = "employee/delete", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteEmployee(@RequestBody Employee employee) {
        return employeeDao.removeEmployee(employee);
    }

    @RequestMapping(value = "employee/updateEmployee", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateEmployees(@RequestBody Employee employee) {
        employeeDao.updateEmployee(employee);
        return true;
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex) {
        logger.error(null, ex);
    }
}
