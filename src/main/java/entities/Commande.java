package entities;

public class Commande {
    private int id;

    public Commande(int i) {
    }



    public int getCommande_id() {
        return id;
    }

    public void setCommande_id(int commande_id) {
        this.id = commande_id;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                '}';
    }


}
