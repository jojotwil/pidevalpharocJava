package alpharoc.pidev.interfaces;

import alpharoc.pidev.entities.demandeloca;
import alpharoc.pidev.entities.loca;

import java.sql.SQLException;
import java.util.List;

public interface Idemandeloca <T>{

    void addEntity2( demandeloca demandeloca);
    void update(T var1) throws SQLException;

    void delete(int var1) throws SQLException;
    public List<T> getAllData();
}
