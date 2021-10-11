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
		File file;
		FileChooser fileChooser = new FileChooser();
		file = fileChooser.showOpenDialog(null);
		System.out.println(file.toPath());
		System.out.println(file.getName());
		if(file == null){
			showAlert("File Not Found");
			success = false;
		}
		else if(!file.toString().endsWith(".txt")){
			showAlert("File must end in \".txt\"");
			success = false;
		}
		else {
			Path p = file.toPath();
			if (TD.uploadFiles(p)){
				textArea.setText(TD.routes.toString());
				success = true;
			} else {
				success = false;
			}
		}
		return success;
	}

	public void showAlert(String message){
		JOptionPane.showMessageDialog(null, message);
	}

}