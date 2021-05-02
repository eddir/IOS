package рф.пинж.ios.controller;

import рф.пинж.ios.model.IModel;
import рф.пинж.ios.view.View;

public abstract class Controller {

    protected IModel model;

    protected View view;

    public Controller(IModel model, View view) {
        this.model = model;
        this.view = view;
    }
}
