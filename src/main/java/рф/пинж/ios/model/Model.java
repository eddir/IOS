package рф.пинж.ios.model;

import java.util.List;

public abstract class Model implements IModel{

    protected int id;
    protected String table;
    protected List<String> columns;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getTable() {
        return this.table;
    }

    @Override
    public List<String> getColumns() {
        return this.columns;
    }
}
