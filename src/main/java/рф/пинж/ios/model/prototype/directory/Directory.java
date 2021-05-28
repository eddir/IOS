package рф.пинж.ios.model.prototype.directory;

import рф.пинж.ios.model.Model;

import java.util.List;

public class Directory extends Model implements IDirectory {

    {
        super.table = "directories";
        super.columns = List.of("id", "title", "created_at");
    }

    private String title;
    private String created_at;
    private Directory parent_dir;
    private List<Directory> children_dir;
//    private list<IosFiles> files;

    // id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // title
    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
    }

    // created date
    public String getCreated_at() {
        return created_at;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    // parent directory
    public Directory getParent_dir() { return parent_dir; }
    public void setParent_dir(Directory parent_dir) { this.parent_dir = parent_dir; }

    // children directory
    public List<Directory> getChildren_dir() { return children_dir; }
    public void setChildren_dir(List<Directory> children_dir) { this.children_dir = children_dir; }

    // files in directory
    // here is your code get/set
}
