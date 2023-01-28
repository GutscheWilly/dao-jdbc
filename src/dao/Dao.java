package dao;

import java.util.List;

public interface Dao<Type> {
    
    void insert(Type type);
    void update(Type type);
    void deleteById(Integer id);
    Type findById(Integer id);
    List<Type> findAll();
}
