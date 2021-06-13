package рф.пинж.ios.model.prototype.ilya;

import рф.пинж.ios.model.Model;

import java.util.List;

public class Group extends Model {

    private String abbreviation;
    private int idSpec;
    private String profile;
    private int courseNumber;
    private int groupNumber;
    private String eduForm;
    private eduLevel level;

    {
        this.table = "group";
        this.columns = List.of("");
    }
}
