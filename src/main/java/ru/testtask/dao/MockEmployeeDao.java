package ru.testtask.dao;

import java.util.ArrayList;
import java.util.List;

import ru.testtask.vo.Employee;

//@Repository
public class MockEmployeeDao implements EmployeeDao
{
	private static List<Employee> list = new ArrayList<>();

	static
	{
		list.add(new Employee(1, "Дмитрий", "Иванов", "Николаевич", 36, "много опыта",
				"некоторое краткое описание 1"));
		list.add(new Employee(2, "Игорь", "Сидоров", "Александрович", 28, "мало опыта",
				"некоторое краткое описание 2"));
		list.add(new Employee(3, "Иван", "Петров", "Иванович", 46, "очень много опыта",
				"некоторое краткое описание 3"));
		list.add(new Employee(4, "Олег", "Сергеев", "Анатолиевич", 55, "мало опыта",
				"некоторое краткое описание 4"));
	}

	@Override
	public List<Employee> getEmployeesList()
	{
		return list;
	}

	@Override
	public boolean addEmployee(Employee employee)
	{
		list.add(employee);
		return true;
	}

	@Override
	public boolean removeEmployee(Employee employee)
	{
		list.remove(employee);
		return true;
	}

	@Override
	public void updateEmployee(Employee employee)
	{
		int index = getIndexById(employee.getId());
		if (index != -1)
			list.set(index, employee);
	}
	
	private int getIndexById(int id)
	{
		for (int i = 0; i < list.size(); i++)
		{
			Employee employee = list.get(i);
			if (employee.getId() == id)
				return i;
		}
		return -1;
	}

	@Override
	public List<Employee> getEmployeesList(Employee employee)
	{
		/*
		 * не стал писать фильтрацию для тестового класса, просто возвращаю
		 * первую строку
		 */
		return list.subList(0, 1);
	}
}
