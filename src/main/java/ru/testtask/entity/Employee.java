package ru.testtask.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee extends AbstractPersistable<Integer> {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "second_name")
    private String secondName;
    private int age;
    private String experience;
    private String description;

    public Employee(String firstName, String lastName, String secondName, int age, String experience,
                    String description) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.age = age;
        this.experience = experience;
        this.description = description;
    }

    public Employee() {
    }

    public static Employee composeFromStringArray(String[] array) {
        Employee employee = new Employee();
        employee.firstName = array[1];
        employee.lastName = array[2];
        employee.secondName = array[3];
        String ageString = array[4];
        if (ageString.isEmpty())
            employee.age = -1;
        else
            employee.age = Integer.parseInt(ageString);
        employee.experience = array[5];
        employee.description = array[6];

        return employee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Employee [id=" + getId() + ", firstName=" + firstName + ", lastName=" + lastName + ", secondName="
                + secondName + ", age=" + age + ", experience=" + experience + ", description=" + description
                + "]";
    }

}