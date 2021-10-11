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
 * @created 07-Oct-2021 11:02:32 AM
 */
public class Trip {

	public int block_id;
	public int direction_id;
	public String route_id;
	public String service_id;
	public String shape_id;
	public String trip_headsign;
	public float trip_id;

	public Trip(String route_id, String service_id, String trip_id, String trip_headsign,
				String direction_id, String block_id,String shape_id){
		this.block_id = (int)makeFloat(block_id);
		this.direction_id = (int)makeFloat(direction_id);
		this.route_id = route_id;
		this.service_id = service_id;
		this.shape_id = shape_id;
		this.trip_headsign = trip_headsign;
		String trip_idString = !trip_id.equals("") ? trip_id.split("_")[0] + "." + trip_id.split("_")[1] : "";
		this.trip_id = makeFloat(trip_idString);
	}

	/**
	 * A better toString for the data dumb for lab 5
	 * @return a text representation of the data in the Trip object
	 */
	public String toString(){
		return "From Trips: \n" +
				"Route_id: " + route_id + "\n" +
				"\tblock_id: " + block_id + "\n" +
				"\tdirection_id: " + direction_id + "\n" +
				"\tservice_id: " + service_id + "\n" +
				"\tshape_id: " + shape_id + "\n" +
				"\ttrip_headsign: " + trip_headsign + "\n" +
				"\ttrip_id: " + trip_id + "\n";
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