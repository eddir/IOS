package рф.пинж.ios.model.prototype.directory;

import рф.пинж.ios.model.Model;

import java.util.List;

public class Directory extends Model implements IDirectory {

    {
        super.table = "directories";
        super.columns = List.of("id", "title", "created_at", "parent_dir");
    }

    private String title;
    private String created_at;
    private Integer parent_dir;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getCreated_at() {
        return created_at;
    }

    @Override
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public Integer getParent_dir() {
        return parent_dir;
    }

    @Override
    public void setParent_dir(int parent_dir) {
        this.parent_dir = parent_dir;
    }

    @Override
    public String toString() {
        return "id: " + id + ", " + title;
    }
}
