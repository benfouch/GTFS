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

import java.sql.Time;
import java.time.*;
import java.util.List;

/**
 * An Object to make data management easier
 * @author Ben Fouch
 * @version 1.0
 * @created 07-Oct-2021 11:02:23 AM
 */
public class StopTime implements GTFSData{

	private final String arrival_time;
	private final String departure_time;
	private final String drop_off_type;
	private final String pickup_type;
	private final String stop_headsign;
	private final String stop_id;
	private final String stop_sequence;
	private final String trip_id;

	public StopTime(List<String> params) {
		int i = 0;
		while (params.size() <= 8){
			params.add("");
		}
		this.trip_id = params.get(i++);
		this.arrival_time = params.get(i++);
		this.departure_time = params.get(i++);
		this.stop_id = params.get(i++);
		this.stop_sequence = params.get(i++);
		this.pickup_type = params.get(i++);
		this.stop_headsign = params.get(i++);
		this.drop_off_type = params.get(i);
	}
	/**
	 * A better toString for the data dumb for lab 5
	 * @return a text representation of the data in the stopTime object
	 */
	public String toString(){
		return trip_id + "," +
				arrival_time + "," +
				departure_time + "," +
				stop_id + "," +
				stop_sequence + "," +
				pickup_type + "," +
				stop_headsign + "," +
				drop_off_type;
	}

	@Override
	public String getHeader() {
		return "trip_id,arrival_time,departure_time,stop_id,stop_sequence," +
				"stop_headsign,pickup_type,drop_off_type\n";
	}

	@Override
	public String getKey() {
		return String.valueOf(trip_id);
	}

	@Override
	public String[] getValues() {
		return toString().split(",");
	}
}