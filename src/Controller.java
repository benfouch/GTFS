import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.File;
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
		// Replace with showing a file explorer and taking the file they choose and giving it to the TransitData obj
		File f = new File("C:\\GTFS Files\\routes.txt");

		Path p = f.toPath();
		if (TD.uploadFiles(p)){
			textArea.setText(TD.routes.toString());
		}

		return false;
	}

}