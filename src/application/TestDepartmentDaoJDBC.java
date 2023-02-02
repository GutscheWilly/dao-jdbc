package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dao.Dao;
import dao.DaoFactory;
import database.DatabaseException;
import entities.Department;

public class TestDepartmentDaoJDBC {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dao<Department> departmentDao = DaoFactory.createDepartmentDao();
        boolean runningProgram = true;

        while (runningProgram) {
            try {
                switch (printMenuOptions(scanner)) {
                    case 1:
                        System.out.print("Enter a ID: ");
                        testFindById(departmentDao, scanner.nextInt());
                        break;
                    case 2:
                        testFindAll(departmentDao);
                        break;
                    case 3:
                        testInsert(departmentDao, scanner);
                        break;
                    case 4:
                        System.out.print("Enter a ID: ");
                        testUpdate(departmentDao, scanner.nextInt(), scanner);
                        break;
                    case 5:
                        System.out.print("Enter a ID: ");
                        testDeleteById(departmentDao, scanner.nextInt());
                        break;
                    case 0:
                        runningProgram = false;
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            }
            catch (InputMismatchException inputMismatchException) {
                clearScreen();
                System.out.println("Input Error!\n");
                scanner.nextLine();
            }
            catch (DatabaseException databaseException) {
                clearScreen();
                System.out.println("Database Error! " + databaseException.getMessage() + "\n");
                scanner.nextLine();
            }
            catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
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
    
    public static void testUpdate(Dao<Department> departmentDao, Integer id, Scanner scanner) {
        System.out.println("TEST 4: update");
        Department department = departmentDao.findById(id);
        System.out.print("Enter the new department's name: ");
        department.setName(scanner.nextLine());
        departmentDao.update(department);
        System.out.println("Department updated! ID = " + department.getId());
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static void testDeleteById(Dao<Department> departmentDao, Integer id) {
        System.out.println("TEST 5: deleteById");
        Department department = departmentDao.findById(id);
        departmentDao.deleteById(id);
        System.out.println("Department deleted: " + department);
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static Integer printMenuOptions(Scanner scanner) {
        System.out.println("Department Data Acess Object");
        System.out.println("1 - Find by ID");
        System.out.println("2 - Find all");
        System.out.println("3 - Insert");
        System.out.println("4 - Update by ID");
        System.out.println("5 - Delete by ID");
        System.out.println("0 - Close program");
        System.out.print("Enter an option: ");
        return scanner.nextInt();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
