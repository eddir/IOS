package рф.пинж.ios.model.prototype.ilya;

import рф.пинж.ios.controller.defaults.ilya.InstituteController;
import рф.пинж.ios.model.Model;
import рф.пинж.ios.repository.ilya.CathedraRepository;

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

    public static String titlesToString(int id) {
        List<String> temp = CathedraRepository.getAllTitles(id);
        StringBuilder str = new StringBuilder();
        int counter = 1;

        for (String test : temp) {
            str.append(counter++).append(". ").append(test).append("\n\r");
        }

        InstituteController.VAL = 1;
        return str.toString();
    }

    @Override
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
