package ru.testtask.repository;

import ru.testtask.entity.Employee;

import java.util.List;

/**
 * Created by Никита on 25.08.2016.
 */
public interface EmployeeRepositoryCustom {

    List<Employee> findAll(Employee employee);

}
