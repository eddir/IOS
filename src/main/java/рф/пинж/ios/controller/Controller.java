package рф.пинж.ios.controller;

import рф.пинж.ios.model.Model;
import рф.пинж.ios.view.View;

public abstract class Controller {

    protected Model model;

    protected View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }
}
