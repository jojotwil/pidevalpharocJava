package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static  Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
         scene = new Scene(loadFXML("hello-view"));
        stage.setScene(scene);
        stage.setFullScreen(true); // Ouvre la fenêtre en mode plein écran

        stage.show();
       // scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }
    static void  SetRoot(String fxml) throws IOException{
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource(fxml +".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

    }
}