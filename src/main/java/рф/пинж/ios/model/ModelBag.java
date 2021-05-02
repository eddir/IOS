package рф.пинж.ios.model;

import java.util.List;

/**
 * Класс заложен на будущее для работы в одном виде с несколькими моделями.
 */
//todo: дописать функционал множественных моделей
public class ModelBag extends Model implements IModel {

    List<IModel> models;

    public ModelBag(List<IModel> models) {
        this.models = models;
    }

    @Override
    public int getId() {
        return 0;
    }
}
