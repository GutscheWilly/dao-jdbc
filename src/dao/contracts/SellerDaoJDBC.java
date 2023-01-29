package dao.contracts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                Department department = createDepartment(resultSet);
                return createSeller(resultSet, department);
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

    public List<Seller> findByDepartment(Department department) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                "SELECT seller.*, department.Name as DepartmentName " +
                "FROM seller INNER JOIN department "                  +
                "ON seller.DepartmentId = department.Id "             +
                "WHERE DepartmentId = ? "                             +
                "ORDER BY Name"
            );
            preparedStatement.setInt(1, department.getId());
            resultSet = preparedStatement.executeQuery();
            return createListOfSellers(resultSet, department);
        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
        finally {
            Database.closeResultSet(resultSet);
            Database.closeStatement(preparedStatement);
        }
    }

    private List<Seller> createListOfSellers(ResultSet resultSet, Department department) throws SQLException {
        List<Seller> listOfSellers = new ArrayList<>();
        while (resultSet.next()) {
            Seller seller = createSeller(resultSet, department);
            listOfSellers.add(seller);
        }
        if (!isListEmpty(listOfSellers)) {
            return listOfSellers;
        }
        return null;
    }

    @Override
    public List<Seller> findAll() {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                "SELECT seller.*, department.Name as DepartmentName " +
                "FROM seller INNER JOIN department "                   +
                "ON seller.DepartmentId = department.Id "             +
                "ORDER BY Name"
            );
            return createListOfSellers(resultSet);
        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
        finally {
            Database.closeResultSet(resultSet);
            Database.closeStatement(statement);
        }
    }

    private List<Seller> createListOfSellers(ResultSet resultSet) throws SQLException {
        Map<Integer, Department> departmentMap = new HashMap<>();
        List<Seller> listOfSellers = new ArrayList<>();

        while (resultSet.next()) {
            Department department = null;
            Integer departmentId = resultSet.getInt("DepartmentId");

            if (departmentMap.containsKey(departmentId)) {
                department = departmentMap.get(departmentId);
            } else {
                department = createDepartment(resultSet);
                departmentMap.put(departmentId, department);
            }

            Seller seller = createSeller(resultSet, department);
            listOfSellers.add(seller);
        }

        if (!isListEmpty(listOfSellers)) {
            return listOfSellers;
        }
        return null;
    }

    private boolean isListEmpty(List<?> list) {
        return list.size() == 0;
    }

    private Seller createSeller(ResultSet resultSet, Department department) throws SQLException {
        Integer id = resultSet.getInt("Id");
        String name = resultSet.getString("Name");
        String email = resultSet.getString("Email");
        Date birthDate = resultSet.getDate("BirthDate");
        Double baseSalary = resultSet.getDouble("BaseSalary");
        return new Seller(id, name, email, birthDate, baseSalary, department);
    }

    private Department createDepartment(ResultSet resultSet) throws SQLException {
        Integer departmentId = resultSet.getInt("DepartmentId");
        String departmentName = resultSet.getString("DepartmentName");
        return new Department(departmentId, departmentName);
    }
}
