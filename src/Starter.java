/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 */


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * The class to get the GUI started
 */
public class Starter extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader();
        Parent mainRoot = mainLoader.load(getClass().getResource("sample.fxml").openStream());
        primaryStage.setScene(new Scene(mainRoot));
        primaryStage.setTitle("Transit App");
        primaryStage.show();
    }



}
