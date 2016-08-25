package ru.testtask.model;

import ru.testtask.vo.Employee;

import java.util.List;

public class EmployeesResponse {

    List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}