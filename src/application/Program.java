package application;

import dao.Dao;
import dao.DaoFactory;
import entities.Seller;

public class Program {

    public static void main(String[] args) throws Exception {
        Dao<Seller> sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(5);
        System.out.println(seller);
    }
}
