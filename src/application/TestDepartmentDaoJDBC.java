package application;

import java.util.List;

import dao.Dao;
import dao.DaoFactory;
import entities.Department;

public class TestDepartmentDaoJDBC {
    
    public static void main(String[] args) {
        Dao<Department> departmentDao = DaoFactory.createDepartmentDao();
        testFindById(departmentDao, 4);
        testFindAll(departmentDao);
    }

    public static void testFindById(Dao<Department> departmentDao, Integer id) {
        System.out.println("TEST 1: findById");
        Department department = departmentDao.findById(id);
        System.out.println(department);
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static void testFindAll(Dao<Department> departmentDao) {
        System.out.println("TEST 2: findAll");
        List<Department> listOfDepartments = departmentDao.findAll();
        listOfDepartments.stream().forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
