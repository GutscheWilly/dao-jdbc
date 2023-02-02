package application;

import java.util.List;
import java.util.Scanner;

import dao.Dao;
import dao.DaoFactory;
import entities.Department;

public class TestDepartmentDaoJDBC {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dao<Department> departmentDao = DaoFactory.createDepartmentDao();
        testFindById(departmentDao, 4);
        testFindAll(departmentDao);
        testInsert(departmentDao, scanner);
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

    public static void testInsert(Dao<Department> departmentDao, Scanner scanner) {
        System.out.println("TEST 3: insert");
        Department department = requestDepartment(scanner);
        departmentDao.insert(department);
        System.out.println("Department inserted! ID = " + department.getId());
        System.out.println("-------------------------------------------------------------------------------------");
    }

    private static Department requestDepartment(Scanner scanner) {
        System.out.print("Enter department's name: ");
        String name = scanner.nextLine();
        return new Department(null, name);
    }  
}
