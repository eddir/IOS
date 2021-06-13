package рф.пинж.ios.repository;

import рф.пинж.ios.Server;
import рф.пинж.ios.model.IModel;
import рф.пинж.ios.model.Model;
import рф.пинж.ios.utils.MainLogger;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * Абстрактный ласс, в котором описана логика работы CRUD
 *
 * @param <T> модель
 */
public abstract class Repository<T extends IModel> implements IRepository<T> {

    // sql2o требует указывать тип модели - название класса. Поэтому эта переменная здесь существует.
    private final Class<T> type;
    // Для полусения данных о модели используется эта переменная.
    private final Model model;

    //todo: исключения убрать
    public Repository(Class<T> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.type = type;
        this.model = (Model) type.getDeclaredConstructor().newInstance();
    }

    public Model getModel() {
        return model;
    }

    public T get(int id) {
        return Server.getInstance().getDatabase().createQuery("SELECT * FROM " + model.getTable() + " WHERE id=:id")
                .addParameter("id", id)
                .executeAndFetchFirst(this.type);
    }

    public Collection<T> getAll() {
        return Server.getInstance().getDatabase().createQuery("SELECT * FROM " + model.getTable())
                .executeAndFetch(this.type);
    }

    public void save(T item) {
        StringBuilder query = new StringBuilder(
                "INSERT INTO " + model.getTable() + "(" + String.join(",", model.getColumns()) + ") VALUES("
        );

        for (String col : model.getColumns()) {
            if (col.equals("created_at") || col.equals("updated_at")) {
                query.append("DEFAULT,");
            } else {
                query.append(String.format(":%s,", col));
            }
        }

        query.deleteCharAt(query.length() - 1);
        query.append(") ON DUPLICATE KEY UPDATE ");

        for (String col : model.getColumns()) {
            query.append(String.format("%s=:%s,", col, col));
        }

        query.deleteCharAt(query.length() - 1);
        MainLogger.getLogger().debug(query.toString());

        Server.getInstance().getDatabase().createQuery(query.toString()).bind(item).executeUpdate();
    }


    public void delete(T item) {
        Server.getInstance().getDatabase().createQuery("DELETE FROM " + this.model.getTable() + " WHERE id=:id")
                .addParameter("id", item.getId())
                .executeUpdate();
    }
}
