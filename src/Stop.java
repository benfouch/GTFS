/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 */


import java.util.List;

/**
 * An Object to make data management easier
 * @author Ben Fouch
 * @version 1.0
 * @created 07-Oct-2021 11:02:21 AM
 */
public class Stop implements GTFSData{

	public String stop_desc;
	public int stop_id;
	public float stop_lat;
	public float stop_lon;
	public String stop_name;

	public Stop(List<String> params){
		int i = 0;
		this.stop_id = (int)makeFloat(params.get(i++));
		this.stop_name = params.get(i++);
		this.stop_desc = params.get(i++);
		this.stop_lat = makeFloat(params.get(i++));
		this.stop_lon = makeFloat(params.get(i));
	}

	/**
	 * A better toString for the data dumb for lab 5
	 * @return a text representation of the data in the Stop object
	 */
	public String toString(){
		return stop_id + "," +
				stop_name + "," +
				stop_desc + "," +
				stop_lat + "," +
				stop_lon;
	}

	@Override
	public String getHeader() {
		return "stop_id,stop_name,stop_desc,stop_lat,stop_lon\n";
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
		return String.valueOf(stop_id);
	}

	@Override
	public String[] getValues() {
		return new String[]{String.valueOf(stop_id), stop_name, stop_desc, String.valueOf(stop_lat),
				String.valueOf(stop_lon)};
	}
}