package рф.пинж.ios.model.prototype.ilya;

import рф.пинж.ios.model.Model;
import рф.пинж.ios.repository.ilya.DirectionRepository;
import рф.пинж.ios.repository.ilya.InstituteRepository;

import java.util.ArrayList;
import java.util.List;

public class Direction extends Model {

    private String title;
    private Cathedra cathedra;
    List<String> profiles = new ArrayList<>();

    {
        this.table= "directions";
        this.columns = List.of("id", "title", "idCathedra");
    }

    public Direction(int id, Cathedra cathedra) {
        this.id = id;
        this.cathedra = cathedra;
        this.title = DirectionRepository.getTitle(id);

        //TODO: Создание профилей
    }

    public static String titlesToString(int idCathedra) {
        List<String> temp = DirectionRepository.getAllTitles(idCathedra);
        StringBuilder str = new StringBuilder();
        int counter = 1;

        for (String test : temp) {
            str.append(counter++).append(". ").append(test).append("\n\r");
        }

        return str.toString();
    }

    public String getTitle() {
        return title;
    }

    public Cathedra getCathedra() {
        return cathedra;
    }
}
