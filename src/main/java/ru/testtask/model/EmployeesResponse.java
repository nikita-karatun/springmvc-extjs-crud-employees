package ru.testtask.model;

import ru.testtask.entity.Employee;

import java.util.List;

public class EmployeesResponse {

    private List<Employee> employees;

    public EmployeesResponse() {
    }

    public EmployeesResponse(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}