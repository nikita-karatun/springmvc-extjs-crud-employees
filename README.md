springmvc-extjs-crud-employees
==============
Example of crud web application on Spring Framework, Ext JS and Hibernate

--------------
ENGLISH
Web application developed in Eclipse IDE, Tomcat 7 used as container, database - mysql.

Account parameters for mysql.
CREATE USER 'mysqluser1'@'localhost' IDENTIFIED BY 'mysqluser1pass';

Database name - test.

Table.
CREATE TABLE `EMPLOYEE` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `second_name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `experience` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

Table can be filled with data by invoking ru.testtask.main.App.main() method (optional).

Interface for handle database queries.
ru.testtask.dao.EmployeeDao
There are two implementations of the interface:
ru.testtask.dao.HibernateEmployeeDao - connect and work with mysql with hibernate;
ru.testtask.dao.MockEmployeeDao - mock class which imitates database queries.

ru.testtask.dao.HibernateEmployeeDao is used by delaults. In order to use mock class instead of ru.testtask.dao.HibernateEmployeeDao one should comment @Repository and uncomment it in ru.testtask.dao.MockEmployeeDao.

--------------
RUSSIAN
Приложение реализовано в Eclipse IDE, в качетсве контейнера используется Tomcat 7, база данных - mysql.

Работа с базой осуществляется через учетную запись
CREATE USER 'mysqluser1'@'localhost' IDENTIFIED BY 'mysqluser1pass';

Имя базы данных - test.

Таблица
CREATE TABLE `EMPLOYEE` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `second_name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `experience` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

Заполнить таблицу тестовыми значениями можно вызовом метода ru.testtask.main.App.main() (опционально).

Взаимодействие с базой определяется интерфейсом
ru.testtask.dao.EmployeeDao
У него есть две реализации:
ru.testtask.dao.HibernateEmployeeDao - подключение и работа с mysql через hibernate;
ru.testtask.dao.MockEmployeeDao - мок-класс имитирующий работу с базой данных.

По умолчанию в качестве источника данных подключен ru.testtask.dao.HibernateEmployeeDao. Чтобы вместо него включить тестовый источник, нужно в ru.testtask.dao.HibernateEmployeeDao закоментировать строку @Repository и в ru.testtask.dao.MockEmployeeDao ее раскоментировать.