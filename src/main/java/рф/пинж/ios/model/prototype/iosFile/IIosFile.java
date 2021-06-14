package рф.пинж.ios.model.prototype.iosFile;

import рф.пинж.ios.model.IModel;

public interface IIosFile extends IModel {

    /**
     * @return название файла
     */
    String getFile_name();

    /**
     * Устанавливает название файла
     * @param file_name - название файла
     */
    void setFile_name(String file_name);

    /**
     * @return id связанной директории
     */
    Integer getIdDirectory();

    /**
     * Связывает файл с директорией
     * @param id - id директории
     */
    void setIdDirectory(int id);

    /**
     * @return url файла
     */
    String getUrlFile();

    /**
     * Устанавливает url файла
     * @param url - url на файл
     */
    void setUrlFile(String url);
}
