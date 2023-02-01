package dao;

import dao.contracts.DepartmentDaoJDBC;
import dao.contracts.SellerDaoJDBC;
import database.Database;
import entities.Department;
import entities.Seller;

public class DaoFactory {
    
    public static Dao<Seller> createSellerDao() {
        return new SellerDaoJDBC(Database.getConnection());
    }

    public static Dao<Department> createDepartmentDao() {
        return new DepartmentDaoJDBC(Database.getConnection());
    }
}
