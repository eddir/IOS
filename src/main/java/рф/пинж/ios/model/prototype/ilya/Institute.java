package рф.пинж.ios.model.prototype.ilya;

import рф.пинж.ios.model.Model;
import рф.пинж.ios.repository.ilya.InstituteRepository;
import java.util.ArrayList;
import java.util.List;

public class Institute extends Model {

    private String title;
    private String abbreviation;
    private List<Cathedra> cathedras = new ArrayList<>();

    //TODO: Изменить на User
    private String director;

    {
        this.table = "institutes";
        this.columns = List.of("id", "title", "abbreviation", "idDirector");
    }

    public Institute(String title, String abbreviation) {
        this.title = title;
        this.abbreviation = abbreviation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    //TODO: Как саня сделает юзера
    public String getDirector() {
        return new String(director);
    }

    /**
     *
     * @return Список всех институтов
     */
    public static String titlesToString() {

        List<String> temp = InstituteRepository.getAllTitles();
        StringBuilder str = new StringBuilder();
        int counter = 1;

        for (String test : temp) {
            str.append(counter++).append(". ").append(test).append("\n\r");
        }

        return str.toString();
    }
}

