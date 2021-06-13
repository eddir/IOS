package рф.пинж.ios.model.prototype.ilya;

import рф.пинж.ios.controller.defaults.ilya.InstituteController;
import рф.пинж.ios.model.Model;
import рф.пинж.ios.repository.ilya.CathedraRepository;
import рф.пинж.ios.repository.ilya.DirectionRepository;

import java.util.ArrayList;
import java.util.List;

public class Cathedra extends Model {

    private String title;
    private Institute institute;
    private List<Direction> directions = new ArrayList<>();
    //TODO: изменить
    private String head;

    {
        this.table = "cathedras";
        this.columns = List.of("id", "title", "idInst", "idHead");
    }

    // Для создания существующей кафедры
    public Cathedra(int id, Institute institute) {
        this.id = id;
        this.institute = institute;
        this.title = CathedraRepository.getTitle(id);

        List<Integer> temp = DirectionRepository.getAllId(id);

        for (Integer value : temp) {
            this.directions.add(new Direction(value, this));
        }

        //TODO: Заведующий
        this.head = null;
    }

    // Для создания новой кафедры
    public Cathedra(int id, Institute institute, String title) {
        this.id = id;
        this.institute = institute;
        this.title = title;

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

    public void addDirection(Direction direction) {
        this.directions.add(direction);
        DirectionRepository.insertNewDirection(direction.getId(), direction.getTitle(), direction.getCathedra().getId());
    }

    public boolean removeDirection(int id) {
        this.directions.remove(DirectionRepository.getTitle(id));
        DirectionRepository.deleteDirection(id);
        return true;
    }

    public Institute getInstitute() {
        return this.institute;
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
