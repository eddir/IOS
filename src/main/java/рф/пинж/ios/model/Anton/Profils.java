package рф.пинж.ios.model.Anton;

import рф.пинж.ios.model.IModel;
import рф.пинж.ios.model.Model;

import java.util.List;

public class Profils extends Model implements IModel {
    {
        this.table = "edupProfils";
        this.columns = List.of("id","description");
    }
    protected String description;
    protected String direction;

    public Profils(int id, String direction) {
        this.description = description;
        this.direction = direction;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @Override
    public String toString(){
        return id + description;
    }
}
