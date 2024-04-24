package alpharoc.pidev.interfaces;

import alpharoc.pidev.entities.VehiculeLouer;

import java.sql.SQLException;
import java.util.List;

public interface IVL <T>{

     void addEntity2(VehiculeLouer vehiculeLouer);
    void update(T var1) throws SQLException;

    void delete(int var1) throws SQLException;
    public List<T> getAllData();
}
