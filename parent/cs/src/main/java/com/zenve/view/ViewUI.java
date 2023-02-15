package com.zenve.view;

import javafx.scene.Parent;
import javafx.stage.Stage;

public abstract class ViewUI extends Stage {
    public Parent root;

    protected abstract void initView();
    protected abstract void initAction();

}
