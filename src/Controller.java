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

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * The Controller for the main gui
 *
 * @author zuberih
 * @version 1.0
 * @created 07-Oct-2021 11:05:21 AM
 */

public class Controller {
    private final TransitData TD = new TransitData();
    private TextArea stopsTextArea;
    private TextArea tripsTextArea;
    private TextArea routesTextArea;
    private TextArea stoptimesTextArea;

    @FXML
    private TableView transitTable;

    @FXML
    private TextArea textArea;

    public boolean editTransitTable() {
        return false;
    }

    public boolean openMap() {
        return false;
    }

    public boolean openTransitTable() {
        return false;
    }

    /**
     * handles find the number of trips through a stop
     */
    public void searchTripsThroughStop() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search Trips Through StopID");
        dialog.setHeaderText("Search for the number of Trips that go through a certain Stop");
        dialog.setContentText("Please enter a Stop_ID:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            textArea.setText(TD.getTripsOnStop(result.get()));
        }
    }

    public void searchRoutesThroughStop() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search Routes Through StopID");
        dialog.setHeaderText("Search for the number of Routes that go through a certain Stop");
        dialog.setContentText("Please enter a Stop_ID:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            textArea.setText(TD.getRoutesThroughStop(result.get()));
        }
    }

    /**
     * Handles finding the next trips through a stop_id
     * timeVarianceMinutes is set to 30 for now but user input will be implemented later
     */
    public void searchNextTrips() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime current = LocalTime.now();
        TextInputDialog time = new TextInputDialog();
        time.setTitle("Time Frame");
        time.setHeaderText("Enter a time frame in minutes");
        time.setContentText("Please enter time frame:");
        Optional<String> timeFrame = time.showAndWait();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search Routes Through StopID");
        dialog.setHeaderText("Search for the number of Routes that go through a certain Stop");
        dialog.setContentText("Please enter a Stop_ID:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            textArea.setText(TD.GetNextTrips(result.get(), form.format(current), Integer.parseInt(timeFrame.get())));
        }

    }

    /**
     * Controller for the upload file button
     * Dumps the uploaded file to the screen after loading it in
     *
     * @return true if the upload was successful
     */
    public boolean uploadFiles() {
        Observer tranTable = new TransitTable(stopsTextArea, tripsTextArea, routesTextArea, stoptimesTextArea);
        TD.attach(tranTable);

        boolean success;
        String filename;
        int correct = 0;
        List<File> files;
        FileChooser fileChooser = new FileChooser();
        files = fileChooser.showOpenMultipleDialog(null);
        // Stops trying to upload if nothing was picked
        if (files == (null)) {
            return false;
        }
        int fileCount = files.size();
        for (File file : files) {
            filename = file.getName();

            if (!file.toString().endsWith(".txt")) {
                showAlert("File must end in \".txt\"", "Error");
            } else {
                Path p = file.toPath();
                try {
                    int numSkipped = TD.downloadFiles(p);
                    correct += 1;
                    fileCount--;
                    showAlert("File " + filename + " uploaded with [" + numSkipped + "] " +
                            "lines skipped. \n" + fileCount + " files left to process.", "Upload progress: ");
                } catch (Exception e) {
                    showAlert(e.getMessage(), "Error");
                }
            }
        }

        success = correct == files.size();
        TD.notifyObservers();
        return success;
    }

    public boolean exportFiles() {
        FileChooser fileChooser = new FileChooser();
        File location = fileChooser.showSaveDialog(null);

        if (location == (null)) {
            return false;
        }

        if (!location.toString().endsWith(".txt")) {
            showAlert("File must end in \".txt\"", "Error");
        }

        String[] splitPath = location.toString().split("\\\\");
        try {
            TD.exportFile(location, splitPath[splitPath.length-1]);
        } catch (IOException e) {
            showAlert("There was an error uploading that file", "Error");
        }

        return true;
    }

    public void showAlert(String message, String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    }