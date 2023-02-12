package com.zenve;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    @Override
    public void start(Stage stage) throws Exception {
        Button button = new Button();
        stage.setScene(new Scene(button));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
