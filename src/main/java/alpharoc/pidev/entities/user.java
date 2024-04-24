package alpharoc.pidev.entities;

import java.util.ArrayList;
import java.util.List;

public class user {
    private int id;
    private List<demandeloca> demandeloca;

    public user(int id, List<alpharoc.pidev.entities.demandeloca> loca) {
        this.id = id;
        this.demandeloca = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<alpharoc.pidev.entities.demandeloca> getLoca() {
        return demandeloca;
    }

    public void setLoca(List<alpharoc.pidev.entities.demandeloca> loca) {
        this.demandeloca.addAll(loca);
    }
}
