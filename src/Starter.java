/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 *
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                    Version 2, December 2004
 *
 * Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>
 * Everyone is permitted to copy and distribute verbatim or modified
 * copies of this license document, and changing it is allowed as long
 * as the name is changed.
 *
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 * TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 * 0. You just DO WHAT THE FUCK YOU WANT TO.
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
