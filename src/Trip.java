

/**
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
		this.block_id = Integer.parseInt(block_id);
		this.direction_id = Integer.parseInt(direction_id);
		this.route_id = route_id;
		this.service_id = service_id;
		this.shape_id = shape_id;
		this.trip_headsign = trip_headsign;
		String trip_idString = trip_id.split("_")[0] + "." + trip_id.split("_")[1];
		this.trip_id = Float.parseFloat(trip_idString);
	}
}