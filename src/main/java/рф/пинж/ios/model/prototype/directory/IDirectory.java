package рф.пинж.ios.model.prototype.directory;

import рф.пинж.ios.model.IModel;

import java.util.List;

/**
 * Интерфейс для работы с директориями
 */
public interface IDirectory extends IModel {

    /**
     * Возвращает название директории
     */
    String getTitle();

    /**
     * Устанавливает название директории
     * @param title - название директории
     */
    void setTitle(String title);

    /**
     * Возвращает дату создания директории
     */
    String getCreated_at();

    /**
     * Устанавливает дату создания
     * @param created_at - дата создания директории
     */
    void setCreated_at(String created_at);

    /**
     * Возвращает родительскую директорию
     */
    Directory getParent_dir();

    /**
     * Устанавливает родительскую директорию
     * @param parent_dir - родительская директория
     */
    void setParent_dir(Directory parent_dir);

    /**
     * Возвращает список дочерних директорий
     */
    List<Directory> getChildren_dir();

    /**
     * Добавляет дочерние директории
     * @param children_dir - массив дочерних директорий
     */
    void setChildren_dir(List<Directory> children_dir);

    // files
}
