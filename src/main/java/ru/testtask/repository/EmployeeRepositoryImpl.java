package ru.testtask.repository;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.testtask.vo.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static ru.testtask.utils.StringUtils.placeBetweenPercentSigns;
import static ru.testtask.utils.StringUtils.stringIsNotNullAndNotEmpty;

/**
 * Created by Никита on 25.08.2016.
 */
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> findAll(Employee employee) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> personRoot = criteria.from(Employee.class);
        criteria.select(personRoot);
        List<Predicate> predicates = new ArrayList<>();

        String firstName = employee.getFirstName();
        if (stringIsNotNullAndNotEmpty(firstName)) {
            predicates.add(criteriaBuilder.like(personRoot.get("firstName"), placeBetweenPercentSigns(firstName)));
        }
        String lastName = employee.getLastName();
        if (stringIsNotNullAndNotEmpty(lastName)) {
            predicates.add(criteriaBuilder.like(personRoot.get("lastName"), placeBetweenPercentSigns(lastName)));
        }
        String secondName = employee.getSecondName();
        if (stringIsNotNullAndNotEmpty(secondName)) {
            predicates.add(criteriaBuilder.like(personRoot.get("secondName"), placeBetweenPercentSigns(secondName)));
        }
        int age = employee.getAge();
        if (age != -1) {
            predicates.add(criteriaBuilder.equal(personRoot.get("age"), age));
        }
        String experience = employee.getExperience();
        if (stringIsNotNullAndNotEmpty(experience)) {
            predicates.add(criteriaBuilder.like(personRoot.get("experience"), placeBetweenPercentSigns(experience)));
        }
        String description = employee.getDescription();
        if (stringIsNotNullAndNotEmpty(description)) {
            predicates.add(criteriaBuilder.like(personRoot.get("description"), placeBetweenPercentSigns(description)));
        }

        if (predicates.size() > 0) {
            Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
            criteria.where(predicatesArray);
        }
        return entityManager.createQuery(criteria).getResultList();
    }

}
