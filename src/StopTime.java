/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
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

	public Time arrival_time;
	public Time departure_time;
	public int drop_off_type;
	public int pickup_type;
	public int stop_headsign;
	public int stop_id;
	public int stop_sequence;
	public float trip_id;

	public StopTime(List<String> params) {
		int i = 0;
		String trip_idString = !params.get(i).equals("") ? params.get(i).split("_")[0] + "." + params.get(i++).split("_")[1] : "";
		this.trip_id = makeFloat(trip_idString);
		this.arrival_time = params.get(i).equals("") ? null :Time.valueOf(params.get(i++));
		this.departure_time = params.get(i).equals("") ? null :Time.valueOf(params.get(i++));
		this.stop_id = (int)makeFloat(params.get(i++));
		this.stop_sequence = (int)makeFloat(params.get(i++));
		this.pickup_type = (int)makeFloat(params.get(i++));
		this.stop_headsign = (int)makeFloat(params.get(i++));
		this.drop_off_type = (int)makeFloat(params.get(i));
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

	/**
	 * A helper to make the data parsing a bit cleaner
	 * @param value the value to be parsed
	 * @return the parsed value
	 */
	private float makeFloat(String value){
		return value.equals("") ? -1 : Float.parseFloat(value);
	}

	@Override
	public String getKey() {
		return String.valueOf(trip_id);
	}
}