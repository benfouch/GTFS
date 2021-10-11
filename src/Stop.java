/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 */


/**
 * An Object to make data management easier
 * @author Ben Fouch
 * @version 1.0
 * @created 07-Oct-2021 11:02:21 AM
 */
public class Stop {

	public String stop_desc;
	public int stop_id;
	public float stop_lat;
	public float stop_lon;
	public String stop_name;

	public Stop(String stop_id, String stop_name, String stop_desc, String stop_lat, String stop_lon){
		this.stop_id = (int)makeFloat(stop_id);
		this.stop_name = stop_name;
		this.stop_desc = stop_desc;
		this.stop_lat = makeFloat(stop_lat);
		this.stop_lon = makeFloat(stop_lon);
	}

	/**
	 * A better toString for the data dumb for lab 5
	 * @return a text representation of the data in the Stop object
	 */
	public String toString(){
		return "From Stops: \n" +
				"stop_id: " + stop_id + "\n" +
				"\tstop_name: " + stop_name + "\n" +
				"\tstop_desc: " + stop_desc + "\n" +
				"\tstop_lat: " + stop_lat + "\n" +
				"\tstop_lon: " + stop_lon + "\n";
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