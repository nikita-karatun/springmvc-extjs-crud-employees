package ru.testtask.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.testtask.dao.EmployeeDao;
import ru.testtask.utils.StringUtils;
import ru.testtask.vo.Employee;

@Controller
@RequestMapping("api")
public class EmployeeController
{
	EmployeeDao employeeDao;

	@Autowired
	public EmployeeController(EmployeeDao employeeDao)
	{
		this.employeeDao = employeeDao;
	}

	@RequestMapping(value = "/index")
	public String index()
	{
		return "index";
	}

	@RequestMapping(value = "employee/save", method = RequestMethod.POST)
	@ResponseBody
	public boolean saveEmployee(@RequestBody Employee employee)
	{
		System.out.println(employee);
		return employeeDao.addEmployee(employee);
	}

	@RequestMapping(value = "employee/loadEmployees")
	@ResponseBody
	public Map<String, List<Employee>> loadAllEmployees()
	{
		Map<String, List<Employee>> employees = new HashMap<String, List<Employee>>();
		employees.put("employees", employeeDao.getEmployeesList());
		return employees;
	}

	@RequestMapping(value = "employee/loadEmployeesWithFilter")
	@ResponseBody
	public Map<String, List<Employee>> loadEmployeesWithFilter(@RequestParam("employee") String[] employeeStringArray)
	{
		/*
		 * по какой то причине ExtJs отправляет этот запрос в ISO-8859-1, не получилось исправть это на клиентской
		 * стороне, поэтому произвожу перекодировку тут
		 */	
		employeeStringArray = StringUtils.correctEncoding(employeeStringArray);
		Employee employee = Employee.composeFromStringArray(employeeStringArray);
		
		Map<String, List<Employee>> employees = new HashMap<String, List<Employee>>();
		employees.put("employees", employeeDao.getEmployeesList(employee));
		return employees;
	}

	@RequestMapping(value = "employee/delete", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteEmployee(@RequestBody Employee employee)
	{
		return employeeDao.removeEmployee(employee);
	}

	@RequestMapping(value = "employee/updateEmployee", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateEmployees(@RequestBody Employee employee)
	{
		employeeDao.updateEmployee(employee);
		return true;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ObjectError> handleMethodArgumentNotValidException(MethodArgumentNotValidException error)
	{
		return error.getBindingResult().getAllErrors();
	}
}
