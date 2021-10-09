import java.sql.Time;
import java.time.*;

/**
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
		String trip_idString = trip_id.split("_")[0] + "." + trip_id.split("_")[1];
		this.trip_id = Float.parseFloat(trip_idString);
		this.arrival_time = Time.valueOf(arrival_time);
		this.departure_time = Time.valueOf(departure_time);
		this.stop_id = Integer.parseInt(stop_id);
		this.stop_sequence = Integer.parseInt(stop_sequence);
		this.stop_headsign = Integer.parseInt(stop_headsign);
		this.pickup_type = Integer.parseInt(pickup_type);
		this.drop_off_type = Integer.parseInt(drop_off_type);
	}
}