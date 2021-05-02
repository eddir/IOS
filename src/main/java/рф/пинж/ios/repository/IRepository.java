package рф.пинж.ios.repository;

import рф.пинж.ios.model.IModel;

import java.util.Collection;

/**
 * Интерфейс реализует модель CRUD:
 *  - CREATE - save(new T())
 *  - READ - get(id)
 *  - UPDATE - save(item)
 *  - DELETE - delete(item)
 * @param <T> модель
 */
public interface IRepository <T extends IModel>{

    /**
     * Извлекает модель из бд по данному id
     * @param id уникальный идентификатор записи
     * @return модель с данными
     */
    T get(int id);

    /**
     * Возвращает все имеющияся записи из бд
     * @return коллекция моделей
     */
    Collection<T> getAll();

    /**
     * Сохраняет модель в бд. Если такая уже существует, то обновляет её новыми данными.
     */
    void save(T item);

    /**
     * Удаляет запись из бд
     * @param item модель
     */
    void delete(T item);

}
