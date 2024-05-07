package Services;

import Entities.Produit;

import java.sql.SQLException;
import java.util.List;

public interface IProduit<T> {
    public void addEntity(T t);
    public void deleteEntity(int id) throws SQLException;
    public void updateEntity(T t );
    public List<T> getAllData();
    public String getNomProduit(int id);

    List<Produit> getSomeData();
}
