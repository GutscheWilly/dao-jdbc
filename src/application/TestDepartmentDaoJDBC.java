package application;

import dao.Dao;
import dao.DaoFactory;
import entities.Department;

public class TestDepartmentDaoJDBC {
    
    public static void main(String[] args) {
        Dao<Department> departmentDao = DaoFactory.createDepartmentDao();
        testFindById(departmentDao, 4);
    }

    public static void testFindById(Dao<Department> departmentDao, Integer id) {
        System.out.println("TEST 1: findById");
        Department department = departmentDao.findById(id);
        System.out.println(department);
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
