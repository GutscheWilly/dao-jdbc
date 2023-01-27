package application;

import java.util.Date;

import entities.Department;
import entities.Seller;

public class Program {

    public static void main(String[] args) throws Exception {
        Department department = new Department(1, "RH");
        Seller seller = new Seller(1, "Willy", "willygutsche@gmail.com", new Date(), 3000.0, department);
        System.out.println(seller);
    }
}
