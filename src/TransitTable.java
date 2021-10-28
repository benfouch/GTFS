/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 */

import javafx.scene.control.TextArea;

import DataObjects.GTFSData;
import java.util.List;

/**
 * @author zuberih
 * @version 1.0
 * @created 07-Oct-2021 11:02:30 AM
 */
public class TransitTable implements Observer {

	public TextArea stopsTextArea;
	public TextArea tripsTextArea;
	public TextArea routesTextArea;
	public TextArea stoptimesTextArea;

	public TransitTable(TextArea stopsTextArea, TextArea tripsTextArea, TextArea routesTextArea, TextArea stoptimesTextArea){
		this.stopsTextArea = stopsTextArea;
		this.routesTextArea = routesTextArea;
		this.tripsTextArea = tripsTextArea;
		this.stoptimesTextArea = stoptimesTextArea;
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

		if (trips != null){
			for (GTFSData data : trips) {
				tripsOut += data.toString() + "\n";
			}
		}
		if (stopTimes != null){
			for (GTFSData data : stopTimes) {
				stopTimesOut += data.toString() + "\n";
			}
		}
		if (stops != null){
			for (GTFSData data : stops) {
				stopsOut += data.toString() + "\n";
			}
		}
		if (routes != null){
			for (GTFSData data : routes) {
				routesOut += data.toString() + "\n";
			}
		}

		//	Set tripsOut box
		tripsTextArea.setText(tripsOut);
		//	Set stopTimesOut box
		stoptimesTextArea.setText(stopTimesOut);
		//	Set stopsOut box
		stopsTextArea.setText(stopsOut);
		//	Set routesOut box
		routesTextArea.setText(routesOut);
	}

	public void updateDisplay(){

	}

}