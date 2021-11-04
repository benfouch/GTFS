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
import javafx.scene.control.TextArea;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.util.HashMap;
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

	@Override
	public void notifyObserver(List<GTFSData> trips, List<GTFSData> stopTimes,
							   List<GTFSData> stops, List<GTFSData> routes) {
		StringBuilder listText = new StringBuilder();
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
				listText.append(data.toString()).append("\n");
				//stopTimesOut += data.toString() + "\n";
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
		//stoptimesTextArea.setText(stopTimesOut);
		stoptimesTextArea.setText(listText.toString());
		//	Set stopsOut box
		stopsTextArea.setText(stopsOut);
		//	Set routesOut box
		routesTextArea.setText(routesOut);


	}

}