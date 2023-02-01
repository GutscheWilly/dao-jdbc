package dao.contracts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.Dao;
import database.Database;
import database.DatabaseException;
import entities.Department;

public class DepartmentDaoJDBC implements Dao<Department> {
    
    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {
        
    }

    @Override
    public void update(Department department) {
        
    }

    @Override
    public void deleteById(Integer id) {
        
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                "SELECT * "        +
                "FROM department " +
                "WHERE Id = ?"
            );
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return createDepartment(resultSet);
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

    @Override
    public List<Department> findAll() {
        return null;
    }

    private Department createDepartment(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("Id");
        String name = resultSet.getString("Name");
        return new Department(id, name);
    }
}
