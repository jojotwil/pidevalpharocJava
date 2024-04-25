package alpharoc.pidev.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class home extends Application {



    @Override
    public void start(Stage primaryStage) {


     try {
            Parent root = FXMLLoader.load(getClass().getResource("/Showvl.fxml"));
         Scene scene = new Scene(root);
         primaryStage.setScene(scene);
         primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {
        launch();

    }
}
