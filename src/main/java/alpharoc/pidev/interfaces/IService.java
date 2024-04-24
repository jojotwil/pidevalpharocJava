package alpharoc.pidev.interfaces;


import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    public void addEntity(T t) throws SQLException;
    public void deleteEntity(T t);
    public void updateEntity(T t,int id);
    public List<T> getAllData();
}