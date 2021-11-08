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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

/**
 * @author zuberih
 * @version 1.0
 * @created 07-Oct-2021 11:02:30 AM
 */
public class TransitTable implements Observer {

	private final ListView<String> stopsTextArea;
	private final ListView<String> tripsTextArea;
	private final ListView<String> routesTextArea;
	private final ListView<String> stoptimesTextArea;

	public TransitTable(ListView<String> stopsTextArea, ListView<String> tripsTextArea, ListView<String> routesTextArea, ListView<String> stoptimesTextArea){
		this.stopsTextArea = stopsTextArea;
		this.routesTextArea = routesTextArea;
		this.tripsTextArea = tripsTextArea;
		this.stoptimesTextArea = stoptimesTextArea;
	}

	@Override
	public void notifyObserver(List<GTFSData> trips, List<GTFSData> stopTimes,
							   List<GTFSData> stops, List<GTFSData> routes) {
		ObservableList<String> outTimes = FXCollections.observableArrayList();
		ObservableList<String> outStops = FXCollections.observableArrayList();
		ObservableList<String> outTrips = FXCollections.observableArrayList();
		ObservableList<String> outRoutes = FXCollections.observableArrayList();

		if (trips != null){
			for (GTFSData data : trips) {
				outTimes.add(data.toString() + ("\n"));
			}
		}

		if (stopTimes != null){
			for (GTFSData data : stopTimes) {
				outTimes.add(data.toString() + ("\n"));
			}
		}

		if (stops != null){
			for (GTFSData data : stops) {
				outStops.add(data.toString() + ("\n"));
			}
		}

		if (routes != null){
			for (GTFSData data : routes) {
				outRoutes.add(data.toString() + ("\n"));
			}
		}

		routesTextArea.setItems(outRoutes);
		tripsTextArea.setItems(outTrips);
		stoptimesTextArea.setItems(outTimes);
		stopsTextArea.setItems(outStops);
	}
}