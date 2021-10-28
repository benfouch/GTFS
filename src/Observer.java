/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 */



import java.util.HashMap;
import java.util.List;

/**
 * @author zuberih
 * @version 1.0
 * @created 07-Oct-2021 11:02:17 AM
 */
public interface Observer {

	public Subject subject = null;


    void notifyObserver(List<GTFSData> trips, List<GTFSData> stopTimes,
                        List<GTFSData> stops, List<GTFSData> routes);
}