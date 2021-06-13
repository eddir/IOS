package рф.пинж.ios.model.prototype.ilya;

import рф.пинж.ios.model.Model;
import java.util.List;

public class Direction extends Model {

    private String title;
    private Cathedra cathedra;
    //List<Profile> profiles = new ArrayList<>();

    {
        this.table= "directions";
        this.columns = List.of("id", "title", "idCathedra");
    }

    public Direction(int id, Cathedra cathedra) {
        this.id = id;
        this.cathedra = cathedra;
    }

    public String getTitle() {
        return title;
    }

    public Cathedra getCathedra() {
        return cathedra;
    }
}
