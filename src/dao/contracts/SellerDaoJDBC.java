package dao.contracts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import dao.Dao;
import database.Database;
import database.DatabaseException;
import entities.Department;
import entities.Seller;

public class SellerDaoJDBC implements Dao<Seller> {

    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {
        
    }

    @Override
    public void update(Seller seller) {
        
    }

    @Override
    public void deleteById(Integer id) {
        
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                "SELECT seller.*, department.Name as DepartmentName " +
                "FROM seller INNER JOIN department "                  +
                "ON seller.DepartmentId = department.Id "             +
                "WHERE seller.Id = ?"
            );
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return createSeller(resultSet);
            }
            return null;
        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
        finally {
            Database.closeResultSet(resultSet);
            Database.closeStatement(preparedStatement);
        }
    }

    private Seller createSeller(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("Id");
        String name = resultSet.getString("Name");
        String email = resultSet.getString("Email");
        Date birthDate = resultSet.getDate("BirthDate");
        Double baseSalary = resultSet.getDouble("BaseSalary");
        Department department = createDepartment(resultSet);
        return new Seller(id, name, email, birthDate, baseSalary, department);
    }

    private Department createDepartment(ResultSet resultSet) throws SQLException {
        Integer departmentId = resultSet.getInt("DepartmentId");
        String departmentName = resultSet.getString("DepartmentName");
        return new Department(departmentId, departmentName);
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
