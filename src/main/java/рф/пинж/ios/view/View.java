package рф.пинж.ios.view;

import рф.пинж.ios.model.IModel;

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
