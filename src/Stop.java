

/**
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
		this.stop_id = Integer.parseInt(stop_id);
		this.stop_name = stop_name;
		this.stop_desc = stop_desc;
		this.stop_lat = Float.parseFloat(stop_lat);
		this.stop_lon = Float.parseFloat(stop_lon);
	}
}