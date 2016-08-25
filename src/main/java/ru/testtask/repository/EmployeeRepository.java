package ru.testtask.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import ru.testtask.vo.Employee;

import java.util.List;

/**
 * Created by Никита on 25.08.2016.
 */
public interface EmployeeRepository extends BaseRepository<Employee, Integer>, EmployeeRepositoryCustom {
}
