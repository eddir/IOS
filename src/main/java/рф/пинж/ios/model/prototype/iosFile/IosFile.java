package рф.пинж.ios.model.prototype.iosFile;

import рф.пинж.ios.model.Model;

import java.util.List;

public class IosFile extends Model implements IIosFile {

    {
        super.table = "files";
        super.columns = List.of("id", "file_name", "idDirectory", "url");
    }

    private String file_name;
    private Integer idDirectory;
    private String url;

    public IosFile() {}

    public IosFile(String file_name, int idDirectory, String url) {
        super();
        this.file_name = file_name;
        this.idDirectory = idDirectory;
        this.url = url;
    }

    @Override
    public String getFile_name() {
        return file_name;
    }

    @Override
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    @Override
    public Integer getIdDirectory() {
        return idDirectory;
    }

    @Override
    public void setIdDirectory(int idDirectory) {
        this.idDirectory = idDirectory;
    }

    @Override
    public String getUrlFile() {
        return url;
    }

    @Override
    public void setUrlFile(String url) {
        this.url = url;
    }
}
