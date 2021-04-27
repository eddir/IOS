package рф.пинж.ios.view;

import рф.пинж.ios.model.Model;
import рф.пинж.ios.controller.Controller;

public class View {

    protected Model model;

    public View(Model model) {
        this.model = model;
    }

    public void registerListener(Controller controller) {

    }

    public String prepare() {
        return "Содержимое окна не установлено";
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
