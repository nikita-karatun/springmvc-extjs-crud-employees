package ru.testtask.main;

import java.util.Iterator;
import java.util.List;

import ru.testtask.dao.HibernateEmployeeDao;
import ru.testtask.vo.Employee;

public class App
{
	public static void main(String[] args)
	{
		HibernateEmployeeDao employeeDao = new HibernateEmployeeDao();

		createTestRecords(employeeDao);
		
//		employeeDao.updateEmployee(7, "Илья", "Иванов", "Николаевич", 36, "много опыта",
//				"некоторое краткое описание 1");

		List<Employee> employees = employeeDao.getEmployeesList();
		list(employees);
		
//		List<Employee> employees = employeeDao.getEmployeesList("митр", "Иван"
//				, "лаев", 36, AgeSign.EQUALS, null, null);
//		list(employees);
		
//		List<Employee> employees = employeeDao.getEmployeesList(null, null
//		, null, 30, AgeSign.GREATE_THEN, null, null);
//		list(employees);
		
		employeeDao.close();
	}

	private static void list(List<Employee> employees)
	{
		for (Iterator<Employee> iterator = employees.iterator(); iterator.hasNext();)
		{
			Employee employee = (Employee) iterator.next();
			System.out.println(employee);
		}
	}

	private static void createTestRecords(HibernateEmployeeDao employeeDao)
	{
		employeeDao.addEmployee(new Employee(0, "Дмитрий", "Иванов", "Николаевич", 36, "много опыта",
				"некоторое краткое описание 1"));
		employeeDao.addEmployee(new Employee(0, "Игорь", "Сидоров", "Александрович", 28, "мало опыта",
				"некоторое краткое описание 2"));
		employeeDao.addEmployee(new Employee(0, "Иван", "Петров", "Иванович", 46, "очень много опыта",
				"некоторое краткое описание 3"));
		employeeDao.addEmployee(new Employee(0, "Олег", "Сергеев", "Анатолиевич", 55, "мало опыта",
				"некоторое краткое описание 4"));
	}
}
