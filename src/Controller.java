/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 */


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * The Controller for the main gui
 *
 * @author zuberih
 * @version 1.0
 * @created 07-Oct-2021 11:05:21 AM
 */
public class Controller {

    public MainGUI m_MainGUI;
    private TransitData TD = new TransitData();

    @FXML
    TextArea textArea;

    @FXML
    TextField searchBar_stop_ID;

    public Controller() {

    }

    public boolean editTransitTable() {
        return false;
    }

    public boolean openMap() {
        return false;
    }

    public boolean openTransitTable() {
        return false;
    }

    public boolean searchTripsThroughStop() {
        textArea.setText(TD.getTripsOnStop(searchBar_stop_ID.getCharacters().toString()));
        return true;
    }


    /**
     * Controller for the upload file button
     * Dumps the uploaded file to the screen after loading it in
     *
     * @return true if the upload was successful
     */
    public boolean uploadFiles() {
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

        for (File file : files) {
            filename = file.getName();
            System.out.println(file.toPath());
            System.out.println(file.getName());

            if (!file.toString().endsWith(".txt")) {
                showAlert("File must end in \".txt\"");
            } else {
                Path p = file.toPath();
                if (TD.downloadFiles(p)) {
                    correct += 1;
                }
            }
            if (filename.equals("routes.txt")) {
                textArea.setText(TD.routes.toString());
            }
            if (filename.equals("stops.txt")) {
                textArea.setText(TD.stops.toString());
            }
            if (filename.equals("stop_times.txt")) {
                textArea.setText(TD.stopTimes.toString());
            }
            if (filename.equals("trips.txt")) {
                textArea.setText(TD.trips.toString());
            }
        }
        success = correct == files.size();
        return success;
    }

    public boolean exportFiles() {
        FileChooser fileChooser = new FileChooser();
        File location = fileChooser.showSaveDialog(null);

        if (location == (null)) {
            return false;
        }

        if (!location.toString().endsWith(".txt")) {
            showAlert("File must end in \".txt\"");
        }

        String[] splitPath = location.toString().split("\\\\");
        try {
            TD.exportFile(location, splitPath[splitPath.length-1]);
        } catch (IOException e) {
            showAlert("There was an error uploading that file");
        }

        return true;
    }


    public void showAlert(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}