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
     * Возвращает id родительской директории
     */
    Integer getParent_dir();

    /**
     * Устанавливает id родительской директории
     * @param parent_dir - id родительской директории
     */
    void setParent_dir(int parent_dir);
}
