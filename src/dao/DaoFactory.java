package dao;

import dao.contracts.SellerDaoJDBC;
import database.Database;
import entities.Seller;

public class DaoFactory {
    
    public static Dao<Seller> createSellerDao() {
        return new SellerDaoJDBC(Database.getConnection());
    }
}
