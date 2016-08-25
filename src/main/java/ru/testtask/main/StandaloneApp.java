package ru.testtask.main;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import ru.testtask.bootstrap.PersistenceJPAConfig;
import ru.testtask.repository.EmployeeRepository;
import ru.testtask.vo.Employee;

@Component
public class StandaloneApp {

    @Configuration
    @ComponentScan(basePackages = "ru.testtask.main")
    @Import(PersistenceJPAConfig.class)
    public static class StandaloneAppConfiguration {
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    public void createTestRecords() {
        employeeRepository.save(new Employee("Дмитрий", "Иванов", "Николаевич", 36, "много опыта",
                "некоторое краткое описание 1"));
        employeeRepository.save(new Employee("Игорь", "Сидоров", "Александрович", 28, "мало опыта",
                "некоторое краткое описание 2"));
        employeeRepository.save(new Employee("Иван", "Петров", "Иванович", 46, "очень много опыта",
                "некоторое краткое описание 3"));
        employeeRepository.save(new Employee("Олег", "Сергеев", "Анатолиевич", 55, "мало опыта",
                "некоторое краткое описание 4"));
    }

    public void clean() {
        employeeRepository.deleteAll();
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(StandaloneAppConfiguration.class);
        StandaloneApp standaloneApp = context.getBean(StandaloneApp.class);
        standaloneApp.createTestRecords();
//        standaloneApp.clean();
    }

}
