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
     * Возвращает дату создания директории
     */
    String getCreated_at();

    /**
     * Возвращает родительскую директорию
     */
    Directory getParent_dir();

    /**
     * Возвращает список дочерних директорий
     */
    List<Directory> getChildren_dir();

    // files
}
