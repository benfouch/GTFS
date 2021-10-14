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
 * @created 07-Oct-2021 11:02:32 AM
 */
public class Trip implements GTFSData{

	public int block_id;
	public int direction_id;
	public String route_id;
	public String service_id;
	public String shape_id;
	public String trip_headsign;
	public float trip_id;

	public Trip(List<String> params){
		int i = 0;
		this.route_id = params.get(i++);
		this.service_id = params.get(i++);
		String trip_idString = !params.get(i).equals("") ? params.get(i).split("_")[0] + "." + params.get(i++).split("_")[1] : "";
		this.trip_id = makeFloat(trip_idString);
		this.trip_headsign = params.get(i++);
		this.direction_id = (int)makeFloat(params.get(i++));
		this.block_id = (int)makeFloat(params.get(i++));
		this.shape_id = params.get(i);
	}

	/**
	 * A better toString for the data dumb for lab 5
	 * @return a text representation of the data in the Trip object
	 */
	public String toString(){
		return route_id + "," +
				service_id + "," +
				trip_id + "," +
				trip_headsign + "," +
				direction_id + "," +
				block_id + "," +
				shape_id;
	}

	@Override
	public String getHeader() {
		return "route_id,service_id,trip_id,trip_headsign," +
				"direction_id,block_id,shape_id\n";
	}

	@Override
	public String getKey() {
		return String.valueOf(trip_id);
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