import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

/**
 * @author zuberih
 * @version 1.0
 * @created 07-Oct-2021 11:05:21 AM
 */
public class Controller {

	public MainGUI m_MainGUI;
	private TransitData TD = new TransitData();

	@FXML
	TextArea textArea;

	public Controller(){

	}

	public void finalize() throws Throwable {

	}

	public boolean editTransitTable(){
		return false;
	}

	public boolean openMap(){
		return false;
	}

	public boolean openTransitTable(){
		return false;
	}

	public boolean uploadFiles(){

		boolean success;
		String filename;
		int correct = 0;
		java.util.List<File> files;
		File file;
		FileChooser fileChooser = new FileChooser();
		files = fileChooser.showOpenMultipleDialog(null);
		for (File value : files) {
			file = value;
			filename = file.getName();
			System.out.println(file.toPath());
			System.out.println(file.getName());
			if (file == null) {
				showAlert("File Not Found");
			} else if (!file.toString().endsWith(".txt")) {
				showAlert("File must end in \".txt\"");
			} else {
				Path p = file.toPath();
				if (TD.uploadFiles(p)) {
					correct += 1;
				}
			}
			if(filename.equals("routes.txt")) {
				textArea.setText(TD.routes.toString());
			}
			if(filename.equals("stops.txt")) {
				textArea.setText(TD.stops.toString());
			}
			if(filename.equals("stop_times.txt")) {
				textArea.setText(TD.stopTimes.toString());
			}
			if(filename.equals("trips.txt")) {
				textArea.setText(TD.trips.toString());
			}
		}
		success = correct == files.size();
		return success;
	}

	public void showAlert(String message){
		JOptionPane.showMessageDialog(null, message);
	}

}