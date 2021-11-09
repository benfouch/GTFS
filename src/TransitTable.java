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
	public void notifyObserver(String trips, String stopTimes,
							   String stops, String routes) {

		//	Set tripsOut box
		tripsTextArea.setText(trips);
		//	Set stopTimesOut box
		stoptimesTextArea.setText(stopTimes);
		//	Set stopsOut box
		stopsTextArea.setText(stops);
		//	Set routesOut box
		routesTextArea.setText(routes);


	}

}