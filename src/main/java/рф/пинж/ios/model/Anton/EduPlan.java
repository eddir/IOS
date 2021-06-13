package рф.пинж.ios.model.Anton;

import рф.пинж.ios.model.IModel;
import рф.пинж.ios.model.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class EduPlan extends Model implements IModel {
    {
        table = "eduPlan";
        columns = List.of("id","description","idProfil");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected String description;
    String profil;
    Set<Object> studentGroups = new HashSet<>();
    Set<Subject> subjects = new HashSet<>();
    public EduPlan(String description){
        super();
        this.description = description;
    }
    @Override
    public String toString(){
        return this.description;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
}
