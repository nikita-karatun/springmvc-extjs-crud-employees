package ru.testtask.repository;


import ru.testtask.entity.Employee;

/**
 * Created by Никита on 25.08.2016.
 */
public interface EmployeeRepository extends BaseRepository<Employee, Integer>, EmployeeRepositoryCustom {
}
