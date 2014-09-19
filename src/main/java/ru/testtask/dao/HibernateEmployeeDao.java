package ru.testtask.dao;

import static ru.testtask.utils.StringUtils.placeBetweenPercentSigns;
import static ru.testtask.utils.StringUtils.stringIsNotNullAndNotEmpty;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.testtask.vo.Employee;

@Repository
public class HibernateEmployeeDao implements EmployeeDao
{
	private static final String UPDATE_ERROR_MESSAGE = "Не удалось обновить запись о работнике";
	private static final String ADD_EMPLOYEE_ERROR_MESSAGE = "Невозможно добавить нового работника.";
	private static final String GET_EMPLOYEES_LIST_ERROR_MESSAGE = "Невозможно получить список работников.";
	private static final String EMPLOYEE_DELETE_ERROR_MESSAGE = "Невозможно удалить работника.";
	private SessionFactory factory;

	public HibernateEmployeeDao()
	{
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder(). applySettings(configuration.getProperties());
		factory = configuration.buildSessionFactory(builder.build());
	}

	@Override
	public boolean addEmployee(Employee employee)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();

			session.save(employee);
			tx.commit();
			return true;
		} catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(ADD_EMPLOYEE_ERROR_MESSAGE);
		} finally
		{
			session.close();
		}
	}

	@Override
	public List<Employee> getEmployeesList()
	{
		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.addOrder(Order.asc("id"));

			@SuppressWarnings("unchecked")
			List<Employee> employees = criteria.list();
			tx.commit();
			return employees;
		} catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(GET_EMPLOYEES_LIST_ERROR_MESSAGE);
		} finally
		{
			session.close();
		}
	}

	@Override
	public List<Employee> getEmployeesList(Employee employee)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.addOrder(Order.asc("id"));

			String firstName = employee.getFirstName();
			if (stringIsNotNullAndNotEmpty(firstName))
				criteria.add(Restrictions.ilike("firstName", placeBetweenPercentSigns(firstName)));
			String lastName = employee.getLastName();
			if (stringIsNotNullAndNotEmpty(lastName))
				criteria.add(Restrictions.ilike("lastName", placeBetweenPercentSigns(lastName)));
			String secondName = employee.getSecondName();
			if (stringIsNotNullAndNotEmpty(secondName))
				criteria.add(Restrictions.ilike("secondName", placeBetweenPercentSigns(secondName)));
			int age = employee.getAge();
			if (age != -1)
				criteria.add(Restrictions.eq("age", age));
			String experience = employee.getExperience();
			if (stringIsNotNullAndNotEmpty(experience))
				criteria.add(Restrictions.ilike("experience", placeBetweenPercentSigns(experience)));
			String description = employee.getDescription();
			if (stringIsNotNullAndNotEmpty(description))
				criteria.add(Restrictions.ilike("description", placeBetweenPercentSigns(description)));

			@SuppressWarnings("unchecked")
			List<Employee> employees = criteria.list();
			tx.commit();
			return employees;
		} catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(GET_EMPLOYEES_LIST_ERROR_MESSAGE);
		} finally
		{
			session.close();
		}
	}

	@Override
	public void updateEmployee(Employee employee)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			session.update(employee);
			tx.commit();
		} catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(UPDATE_ERROR_MESSAGE);
		} finally
		{
			session.close();
		}
	}

	@Override
	public boolean removeEmployee(Employee employee)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			session.delete(employee);
			tx.commit();
			return true;
		} catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(EMPLOYEE_DELETE_ERROR_MESSAGE);
		} finally
		{
			session.close();
		}
	}

	public void close()
	{
		factory.close();
	}
}