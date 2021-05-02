package рф.пинж.ios.view;

import рф.пинж.ios.model.IModel;
import рф.пинж.ios.controller.Controller;

public class View {

    protected IModel model;

    public View(IModel model) {
        this.model = model;
    }

    public String prepare() {
        return "Содержимое окна не установлено";
    }

    public IModel getModel() {
        return model;
    }

    public void setModel(IModel model) {
        this.model = model;
    }
}
