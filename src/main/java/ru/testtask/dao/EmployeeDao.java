package ru.testtask.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import ru.testtask.vo.Employee;

@Component
public interface EmployeeDao
{
	boolean addEmployee(Employee employee);

	List<Employee> getEmployeesList();
	
	List<Employee> getEmployeesList(Employee employee);

	void updateEmployee(Employee employee);

	boolean removeEmployee(Employee employee);
}