/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 */

import javax.naming.NameNotFoundException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ben Fouch
 * @version 1.0
 * @created 07-Oct-2021 11:02:27 AM
 * I will be
 */
public class TransitData implements Subject {
	//region vars
	public HashMap<Object, GTFSData> routes;
	public List<GTFSData> routeList;
	public HashMap<Object, GTFSData> stops;
	public List<GTFSData> stopsList;
	public HashMap<Object, GTFSData> stopTimes;
	public List<GTFSData> timesList;
	public HashMap<Object, GTFSData> trips;
	public List<GTFSData> tripsList;
	public Stop m_Stop;
	public Controller m_Controller;
	public StopTime m_StopTime;
	public Trip m_Trip;
	public Route m_Route;
	public TransitTable m_TransitTable;
	public Map m_Map;
	HashMap<Object, GTFSData> gtfsMap;
	List<GTFSData> gtfsList;
	//endregion

	//region not Implemented
	public boolean exportFiles() {
		return false;
	}

	/**
	 * @param trip_id
	 */
	public float getAverageSpeed(float trip_id) {
		return 0;
	}

	/**
	 * @param stop_id
	 */
	public int getNumTrips(int stop_id) {
		return 0;
	}

	/**
	 * @param trip_id
	 */
	public float getTripDistance(float trip_id) {
		return 0;
	}

	/**
	 * @param route_id
	 */
	public boolean plotBus(String route_id) {
		return false;
	}

	public boolean plotMap() {
		return false;
	}

	/**
	 * @param route_id
	 */
	public int searchRoute(String route_id) {
		return 0;
	}

	/**
	 * @param route_id
	 */
	public float searchRouteForTrips(String route_id) {
		return 0;
	}

	/**
	 * @param stop_id
	 */
	public String searchStop(int stop_id) {
		return "";
	}

	/**
	 * @param stop_id
	 */
	public float searchStopTrip(int stop_id) {
		return 0;
	}

	/**
	 * @param type
	 * @param oldAtribute
	 * @param newAtribute
	 */
	public boolean updateAttributes(String type, String oldAtribute, String newAtribute) {
		return false;
	}
	//endregion

	/**
	 * Saves in the File from specified path into the data structure of the program
	 *
	 * @param path The path of the file being uploaded
	 */
	public boolean downloadFiles(Path path) {
		gtfsMap = new HashMap<>();
		gtfsList = new LinkedList<>();
		GTFSData newObj;
		boolean firstLine = true;
		List<String> splitLine;
		String[] pathList = path.toString().split("\\\\");
		String fileName = pathList[pathList.length - 1];

		try (Scanner scanner = new Scanner(path)) {
			while (scanner.hasNextLine()) {
				splitLine = Arrays.stream(scanner.nextLine().split(",")).collect(Collectors.toList());
				splitLine.add("");
				if (!firstLine) {
					newObj = setNewObj(fileName, splitLine);
					gtfsMap.put(newObj.getKey(), newObj);
					gtfsList.add(newObj);
				}
				firstLine = false;
			}
		} catch (Exception e) {
			System.out.println("Error in <TransitData.saveGTFSFile()>");
			return false;
		}
		setDataStructures(fileName);
		return true;
	}

	//region not Implemented
	/**
	 * 
	 * @param observer
	 */
	public void attach(Observer observer){

	}

	/**
	 * 
	 * @param observer
	 */
	public void detach(Observer observer){

	}

	public void notifyObservers(){

	}
	//endregion

	private GTFSData setNewObj(String fileName, List<String> splitLine) throws NameNotFoundException {
		switch (fileName) {
			case "routes.txt":
				return new Route(splitLine);
			case "stop_times.txt":
				return new StopTime(splitLine);
			case "stops.txt":
				return new Stop(splitLine);
			case "trips.txt":
				return new Trip(splitLine);
			default:
				throw new NameNotFoundException("File Name" + fileName + "is not valid");
		}
	}

	private void setDataStructures(String fileName){
		switch (fileName) {
			case "routes.txt":
				routes = gtfsMap;
				routeList = gtfsList;
				break;
			case "stop_times.txt":
				stopTimes = gtfsMap;
				stopsList = gtfsList;
				break;
			case "stops.txt":
				stops = gtfsMap;
				stopsList = gtfsList;
				break;
			case "trips.txt":
				trips = gtfsMap;
				tripsList = gtfsList;
				break;
		}
	}

	public void exportFile(File location, String name) throws IOException {
		List<GTFSData> dataObject = new LinkedList<>();
		switch (name){
			case "routes.txt":
				dataObject = routeList;
				break;
			case "stops.txt":
				dataObject = stopsList;
				break;
			case "stop_times.txt":
				dataObject = timesList;
				break;
			case "trips.txt":
				dataObject = tripsList;
				break;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(location))) {
			writer.write(dataObject.get(0).getHeader());
			for (GTFSData data : dataObject) {
				writer.write(data.toString() + "\n");
			}
		}
	}

	public boolean isTrips(String line) {
		String validLine = "route_id,service_id,trip_id,trip_headsign,direction_id,block_id,shape_id";
		return line.equals(validLine);
	}

	public boolean isStops(String line) {
		String validLine = "stop_id,stop_name,stop_desc,stop_lat,stop_lon";
		return line.equals(validLine);
	}

	public boolean isStopTimes(String line) {
		String validLine = "trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type";
		return line.equals(validLine);
	}

	public boolean isRoutes(String line) {
		String validLine = "route_id,agency_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_color,route_text_color";
		return line.equals(validLine);
	}

	public static boolean isTripsLine(ArrayList<String> list) {
		int counter = 0;
		if (list.size() == 7) {
			if (!list.get(0).isEmpty()) {
				counter += 1;
			}
			if (!list.get(1).isEmpty()) {
				counter += 1;
			}
			if (!list.get(2).isEmpty()) {
				counter += 1;
			}
			if (!list.get(3).isEmpty()) {
				counter += 1;
			}
			if (!list.get(4).isEmpty()) {
				counter += 1;
			}
			if (!list.get(5).isEmpty()) {
				counter += 1;
			}
			if (!list.get(6).isEmpty()) {
				counter += 1;
			}
		}
		return counter == 7;
	}

	public static boolean isRoutesLine(ArrayList<String> list) {
		int counter = 0;
		if (list.size() == 9) {
			if (!list.get(0).isEmpty()) {
				counter += 1;
			}
			if (!list.get(1).isEmpty()) {
				counter += 1;
			}
			if (!list.get(2).isEmpty()) {
				counter += 1;
			}
			if (!list.get(3).isEmpty()) {
				counter += 1;
			}
			if (!list.get(5).isEmpty()) {
				counter += 1;
			}
			if (!list.get(7).isEmpty()) {
				counter += 1;
			}
		}
		return counter == 6;
	}

	public static boolean isStopTimesLine(ArrayList<String> list) {
		int counter = 0;
		if (list.size() == 8) {
			if (!list.get(0).isEmpty()) {
				counter += 1;
			}
			if (!list.get(1).isEmpty()) {
				counter += 1;
			}
			if (!list.get(2).isEmpty()) {
				counter += 1;
			}
			if (!list.get(3).isEmpty()) {
				counter += 1;
			}
			if (!list.get(4).isEmpty()) {
				counter += 1;
			}
			if (!list.get(6).isEmpty()) {
				counter += 1;
			}
			if (!list.get(7).isEmpty()) {
				counter += 1;
			}
		}
		return counter == 7;
	}

	public static boolean isStopsLine(ArrayList<String> list) {
		int counter = 0;
		if (list.size() == 5) {
			if (!list.get(0).isEmpty()) {
				counter += 1;
			}
			if (!list.get(1).isEmpty()) {
				counter += 1;
			}
			if (!list.get(3).isEmpty()) {
				counter += 1;
			}
			if (!list.get(4).isEmpty()) {
				counter += 1;
			}
		}
		return counter == 4;
	}
	
	public int getTripsOnStop(int stop_id){
		int i = 0;
		for (GTFSData time : timesList){
			if (time.getValues()[3].equals(String.valueOf(stop_id))){
				i++;
			}
		}
		return i;
	}
}