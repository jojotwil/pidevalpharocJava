package alpharoc.pidev.interfaces;

import alpharoc.pidev.entities.VehiculeLouer;
import alpharoc.pidev.entities.loca;

import java.sql.SQLException;
import java.util.List;

public interface Iloca <T>{

    void addEntity2( loca loca);
    void update(T var1) throws SQLException;

    void delete(int var1) throws SQLException;
    public List<T> getAllData();
}