/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 */

import java.sql.Time;
import java.time.*;

/**
 * An Object to make data management easier
 * @author Ben Fouch
 * @version 1.0
 * @created 07-Oct-2021 11:02:23 AM
 */
public class StopTime {

	public Time arrival_time;
	public Time departure_time;
	public int drop_off_type;
	public int pickup_type;
	public int stop_headsign;
	public int stop_id;
	public int stop_sequence;
	public float trip_id;

	public StopTime(String trip_id, String arrival_time,String departure_time, String stop_id,
					String stop_sequence, String stop_headsign,String pickup_type,String drop_off_type) {
		String trip_idString = !trip_id.equals("") ? trip_id.split("_")[0] + "." + trip_id.split("_")[1] : "";
		this.trip_id = makeFloat(trip_idString);
		this.arrival_time = arrival_time.equals("") ? null :Time.valueOf(arrival_time);
		this.departure_time = departure_time.equals("") ? null :Time.valueOf(departure_time);
		this.stop_id = (int)makeFloat(stop_id);
		this.stop_sequence = (int)makeFloat(stop_sequence);
		this.stop_headsign = (int)makeFloat(stop_headsign);
		this.pickup_type = (int)makeFloat(pickup_type);
		this.drop_off_type = (int)makeFloat(drop_off_type);
	}

	/**
	 * A better toString for the data dumb for lab 5
	 * @return a text representation of the data in the stopTime object
	 */
	public String toString(){
		return "From StopTimes: \n" +
				"\ttrip_id: " + trip_id + "\n" +
				"\tarrival_time: " + arrival_time + "\n" +
				"\tdeparture_time: " + departure_time + "\n" +
				"\tstop_id: " + stop_id + "\n" +
				"\tstop_sequence: " + stop_sequence + "\n" +
				"\tstop_headsign: " + stop_headsign + "\n" +
				"\tpickup_type: " + pickup_type + "\n" +
				"\tdrop_off_type: " + drop_off_type + "\n";
	}

	/**
	 * A helper to make the data parsing a bit cleaner
	 * @param value the value to be parsed
	 * @return the parsed value
	 */
	private float makeFloat(String value){
		return value.equals("") ? -1 : Float.parseFloat(value);
	}

}