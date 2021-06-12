package рф.пинж.ios.model.prototype.ilya;

import рф.пинж.ios.model.Model;
import java.util.ArrayList;
import java.util.List;

public class Cathedra extends Model {

    private String title;
    private Institute institute;
    //private List<Direction> directions = new ArrayList<>();
    //TODO: изменить
    private String head;

    {
        this.table = "cathedras";
        this.columns = List.of("id", "title", "idInst", "idHead");
    }

    public Cathedra(int id, Institute institute) {
        this.id = id;
        this.institute = institute;

        //TODO: Заведующий
        this.head = null;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
