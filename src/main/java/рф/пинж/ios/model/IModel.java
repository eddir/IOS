package рф.пинж.ios.model;

import java.util.List;

/**
 * Интерфейс для работы с моделями и данными
 */
public interface IModel {

    /**
     * Возвращает уникальный идентификатор записи в бд
     */
    int getId();

    /**
     * Возвращает название таблицы данной модели в бд
     */
    String getTable();

    /**
     * Возвращает список с названиями всех столбцов в таблице модели
     */
    List<String> getColumns();
}
