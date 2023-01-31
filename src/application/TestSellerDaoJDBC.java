package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.DaoFactory;
import dao.contracts.SellerDaoJDBC;
import entities.Department;
import entities.Seller;

public class TestSellerDaoJDBC {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        SellerDaoJDBC sellerDao = (SellerDaoJDBC) DaoFactory.createSellerDao();
        testFindById(sellerDao);
        testFindByDepartment(sellerDao);
        testFindAll(sellerDao);
        testInsert(sellerDao);
        testUpdate(sellerDao);
        testDelete(sellerDao, scanner);
    }

    public static void testFindById(SellerDaoJDBC sellerDaoJDBC) {
        System.out.println("TEST 1: findById");
        Seller seller = sellerDaoJDBC.findById(5);
        System.out.println(seller);
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static void testFindByDepartment(SellerDaoJDBC sellerDao) {
        System.out.println("TEST 2: findByDepartment");
        Department department = new Department(2, "Eletronics");
        List<Seller> listOfSellers = sellerDao.findByDepartment(department);
        for (Seller seller : listOfSellers) {
            System.out.println(seller);
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static void testFindAll(SellerDaoJDBC sellerDaoJDBC) {
        System.out.println("TEST 3: findAll");
        List<Seller> listOfSellers = sellerDaoJDBC.findAll();
        for (Seller seller : listOfSellers) {
            System.out.println(seller);
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static void testInsert(SellerDaoJDBC sellerDaoJDBC) {
        System.out.println("TEST 4: insert");
        Department department = new Department(2, "Eletronics");
        Seller seller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerDaoJDBC.insert(seller);
        System.out.println("Seller inserted! Id = " + seller.getId());
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static void testUpdate(SellerDaoJDBC sellerDaoJDBC) {
        System.out.println("TEST 5: update");
        Seller seller = sellerDaoJDBC.findById(1);
        seller.setName("Willy");
        seller.setEmail("willygutsche@gmail.com");
        sellerDaoJDBC.update(seller);
        System.out.println("Seller updated! Id = " + seller.getId());
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static void testDelete(SellerDaoJDBC sellerDaoJDBC, Scanner scanner) {
        System.out.println("TEST 6: deleteById");
        System.out.print("Enter an ID: ");
        Integer id = scanner.nextInt();
        sellerDaoJDBC.deleteById(id);
        System.out.println("Seller deleted! Id = " + id);
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
