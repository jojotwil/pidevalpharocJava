package Interfaces;


import java.sql.SQLException;
import java.util.List;

public interface Iloca <T>{

    void addEntity2(T t);
    void update(T var1) throws SQLException;

    void delete(int var1) throws SQLException;
    public List<T> getAllData();
}
