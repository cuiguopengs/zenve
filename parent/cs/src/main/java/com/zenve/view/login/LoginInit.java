package com.zenve.view.login;

import com.zenve.view.ViewUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginInit extends ViewUI {
    private static final String RESOURCE_NAME = "/fxml/login/login.fxml";

    private Button loginButton;

    LoginInit() {
        initView();
        initAction();
    }

    @Override
    protected void initView() {
        try {
            root = FXMLLoader.load(getClass().getResource(RESOURCE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        this.getIcons().add(new Image("/fxml/chat/img/head/logo.png"));
    }

    @Override
    protected void initAction() {

    }

}
