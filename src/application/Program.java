package application;

import java.util.Date;
import java.util.List;

import dao.DaoFactory;
import dao.contracts.SellerDaoJDBC;
import entities.Department;
import entities.Seller;

public class Program {

    public static void main(String[] args) throws Exception {
        SellerDaoJDBC sellerDao = (SellerDaoJDBC) DaoFactory.createSellerDao();
        test1(sellerDao);
        test2(sellerDao);
        test3(sellerDao);
        test4(sellerDao);
    }

    public static void test1(SellerDaoJDBC sellerDao) {
        System.out.println("TEST 1: findById");
        Seller seller = sellerDao.findById(5);
        System.out.println(seller);
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static void test2(SellerDaoJDBC sellerDao) {
        System.out.println("TEST 2: findByDepartment");
        Department department = new Department(2, "Eletronics");
        List<Seller> listOfSellers = sellerDao.findByDepartment(department);
        for (Seller seller : listOfSellers) {
            System.out.println(seller);
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static void test3(SellerDaoJDBC sellerDaoJDBC) {
        System.out.println("TEST 3: findAll");
        List<Seller> listOfSellers = sellerDaoJDBC.findAll();
        for (Seller seller : listOfSellers) {
            System.out.println(seller);
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static void test4(SellerDaoJDBC sellerDaoJDBC) {
        System.out.println("TEST 4: insert");
        Department department = new Department(2, "Eletronics");
        Seller seller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerDaoJDBC.insert(seller);
        System.out.println("Seller inserted! Id = " + seller.getId());
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
