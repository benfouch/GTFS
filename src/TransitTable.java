/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 */

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import DataObjects.GTFSData;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.HashMap;
import java.util.List;

/**
 * @author zuberih
 * @version 1.0
 * @created 07-Oct-2021 11:02:30 AM
 */
public class TransitTable implements Observer {

	TextArea textArea;

	public TransitTable(TextArea area){
		textArea = area;
	}

	public void createUserInterface(){

	}

	@Override
	public void notifyObserver(List<GTFSData> trips, List<GTFSData> stopTimes,
							   List<GTFSData> stops, List<GTFSData> routes) {
		String tripsOut = "";
		String stopTimesOut = "";
		String stopsOut = "";
		String routesOut = "";

		for (GTFSData data : trips) {
			tripsOut += data.toString() + "\n";
		}

		for (GTFSData data : stopTimes) {
			stopTimesOut += data.toString() + "\n";
		}

		for (GTFSData data : stops) {
			stopsOut += data.toString() + "\n";
		}

		for (GTFSData data : routes) {
			routesOut += data.toString() + "\n";
		}

		//	Set tripsOut box
		//	Set stopTimesOut box
		//	Set stopsOut box
		//	Set routesOut box
	}

	public void updateDisplay(){

	}

}