package рф.пинж.ios.model.prototype.ilya;

import рф.пинж.ios.model.Model;
import рф.пинж.ios.repository.ilya.CathedraRepository;
import рф.пинж.ios.repository.ilya.InstituteRepository;
import java.util.ArrayList;
import java.util.List;

public class Institute extends Model {

    private String title;
    private String abbreviation;
    private List<Cathedra> cathedras = new ArrayList<>();
    //TODO: Изменить на User, коннект с Саньком
    private String director;

    {
        this.table = "institutes";
        this.columns = List.of("id", "title", "abbreviation", "idDirector");
    }

    public Institute(int id) {
        this.id = id;
        this.title = InstituteRepository.getPart(id, 1);
        this.abbreviation = InstituteRepository.getPart(id, 2);

        List<Integer> list = CathedraRepository.getAllId(id);

        for (Integer value : list) {
            this.cathedras.add(new Cathedra(value, this));
        }

        //TODO: продумать директора
        this.director = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        InstituteRepository.update("title", id, title);
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

    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Красивое формировение всех институтов в список
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

    @Override
    public String toString() {
        return id + " " + title + " " + abbreviation;
    }
}

