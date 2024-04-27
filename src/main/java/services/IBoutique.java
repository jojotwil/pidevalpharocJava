package services;

import java.sql.SQLException;
import java.util.List;

public interface IBoutique<T> {
    public void addEntity(T t);
    public void deleteEntity(int id) throws SQLException;
    public void updateEntity(T t );
    public List<T> getAllData();
    public String getNomBoutique(int id);

}
