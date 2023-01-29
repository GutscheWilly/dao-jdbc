package application;

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
}
