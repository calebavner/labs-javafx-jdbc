package com.midasvision.javafxjdbc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        ScrollPane scrollPane = fxmlLoader.load();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scene = new Scene(scrollPane);
        stage.setTitle("MidasSeller");
        stage.setScene(scene);
        stage.show();
    }

    public static Scene getMainScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }
}