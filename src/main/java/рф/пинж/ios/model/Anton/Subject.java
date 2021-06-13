package рф.пинж.ios.model.Anton;

import рф.пинж.ios.model.Model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subject extends Model {
    {
        this.table = "subject";
        this.columns = List.of("id","description","idTeacher","semestrNumber","amountOfHourPerWeekEven","amountOfHourPerWeekOdd","amountOfHourPerSemestr","PathToDirect");
    }
    //String profil;
    protected String description;
    protected int semestrNumber;
    protected String teacher;
    protected int amountOfHourPerWeekEven;
    protected int amountOfHourPerWeekOdd;
    protected int amountOfHourPerSemestr;
    protected Set<typeMark> typeOfMarks = new HashSet<typeMark>(); //Элементы должны быть уникальны!
    protected int pathToDirect;
    public Subject(){
        super();
    }
    /*
    public Subject(String profil, String description) {
        this();
        this.profil = profil;
        this.description = description;
    }
    */
    public Subject(String description,int id,int idDir) {
        this();
        this.id = id;
        this.pathToDirect = idDir;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSemestrNumber() {
        return semestrNumber;
    }

    public void setSemestrNumber(int semestrNumber) {
        this.semestrNumber = semestrNumber;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getAmountOfHourPerWeekEven() {
        return amountOfHourPerWeekEven;
    }

    public void setAmountOfHourPerWeekEven(int amountOfHourPerWeekEven) {
        this.amountOfHourPerWeekEven = amountOfHourPerWeekEven;
    }

    public int getAmountOfHourPerWeekOdd() {
        return amountOfHourPerWeekOdd;
    }

    public void setAmountOfHourPerWeekOdd(int amountOfHourPerWeekOdd) {
        this.amountOfHourPerWeekOdd = amountOfHourPerWeekOdd;
    }

    public int getAmountOfHourPerSemestr() {
        return amountOfHourPerSemestr;
    }

    public void setAmountOfHourPerSemestr(int amountOfHourPerSemestr) {
        this.amountOfHourPerSemestr = amountOfHourPerSemestr;
    }

    public Set<typeMark> getTypeOfMarks() {
        return typeOfMarks;
    }

    public void setTypeOfMarks(Set<typeMark> typeOfMarks) {
        this.typeOfMarks = typeOfMarks;
    }

    public int getPathToDirect() {
        return pathToDirect;
    }

    public void setPathToDirect(int pathToDirect) {
        this.pathToDirect = pathToDirect;
    }

}
