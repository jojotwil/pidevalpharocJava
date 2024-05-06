package Interfaces;

import java.util.List;

public interface DemandetrocService <T>{
    public void addDemande(T t);
    public void deleteDemande(T t);
    public void updateDemande(T t);
    public List<T> getAllDemandes();
    public T getDemandeById(int id);
    public List<T> getDemandesByPostid(int id);
    public T getDemandeByPostid(int id);

}
