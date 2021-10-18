/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 */

import javax.naming.InvalidNameException;
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
	private List<String> loadedStructures = new LinkedList<>();

	//endregion

	//region not Implemented
	/**
	 * not Implemented
	 * @param observer
	 */
	public void attach(Observer observer){

	}

	/**
	 * not Implemented
	 * @param observer
	 */
	public void detach(Observer observer){

	}

	/**
	 * not Implemented
	 */
	public void notifyObservers(){

	}
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

	//region Files I/O
	/**
	 * Saves in the File from specified path into the data structures of the program
	 *
	 * @param path The path of the file being uploaded
	 * @return the number of line that was skipped in the upload process
	 */
	public int downloadFiles(Path path) throws InvalidNameException, NameNotFoundException {
		gtfsMap = new HashMap<>();
		gtfsList = new LinkedList<>();
		GTFSData newObj;
		boolean firstLine = true;
		List<String> splitLine;
		String[] pathList = path.toString().split("\\\\");
		String fileName = pathList[pathList.length - 1];
		int numSkipped = 0;

		try (Scanner scanner = new Scanner(path)) {
			if (!isValidHeader(fileName, scanner.nextLine())){
				throw new InvalidNameException("Invalid Header");
			}
			while (scanner.hasNextLine()) {
				splitLine = Arrays.stream(scanner.nextLine().split(",")).collect(Collectors.toList());
				splitLine.add("");
				if (!firstLine) {
					if (isValidLine(fileName, splitLine)){
						newObj = setNewObj(fileName, splitLine);
						gtfsMap.put(newObj.getKey(), newObj);
						gtfsList.add(newObj);
					} else {
						numSkipped++;
					}
				}
				firstLine = false;
			}
		} catch (IOException e){
			return -1;
		}
		setDataStructures(fileName);
		return numSkipped;
	}

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
				loadedStructures.add("routes");
				break;
			case "stop_times.txt":
				stopTimes = gtfsMap;
				timesList = gtfsList;
				loadedStructures.add("stopTimes");
				break;
			case "stops.txt":
				stops = gtfsMap;
				stopsList = gtfsList;
				loadedStructures.add("stops");
				break;
			case "trips.txt":
				trips = gtfsMap;
				tripsList = gtfsList;
				loadedStructures.add("trips");
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
    //endregion

	//region File validation
	private boolean isValidLine(String fileName, List<String> firstLine){
		switch (fileName) {
			case "routes.txt":
				return isRoutesLine((ArrayList<String>) firstLine);
			case "stop_times.txt":
				return isStopTimesLine((ArrayList<String>) firstLine);
			case "stops.txt":
				return isStopsLine((ArrayList<String>) firstLine);
			case "trips.txt":
				return isTripsLine((ArrayList<String>) firstLine);
		}
		return false;
	}

	private boolean isValidHeader(String fileName, String firstLine){
		switch (fileName) {
			case "routes.txt":
				return isRoutes(firstLine);
			case "stop_times.txt":
				return isStopTimes(firstLine);
			case "stops.txt":
				return isStops(firstLine);
			case "trips.txt":
				return isTrips(firstLine);
		}
		return false;
	}


	/**
	 * Validates the header for trips
	 * @param line the line to be checked
	 * @return true if the header matches ethe expected
	 */
	public boolean isTrips(String line) {
		return line.equals("route_id,service_id,trip_id,trip_headsign,direction_id,block_id,shape_id");
	}

	/**
	 * Validates the header for stops
	 * @param line the line to be checked
	 * @return true if the header matches ethe expected
	 */
	public boolean isStops(String line) {
		return line.equals("stop_id,stop_name,stop_desc,stop_lat,stop_lon");
	}

	/**
	 * Validates the header for stop times
	 * @param line the line to be checked
	 * @return true if the header matches ethe expected
	 */
	public boolean isStopTimes(String line) {
		return line.equals("trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type");
	}

	/**
	 * Validates the header for routes
	 * @param line the line to be checked
	 * @return true if the header matches ethe expected
	 */
	public boolean isRoutes(String line) {
		return line.equals("route_id,agency_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_color,route_text_color");
	}

	/**
	 * Checks to make sure the trips line is filled out where it needs to be
	 * @param list the list of inputs in the line
	 * @return true is it matches the expected
	 */
	public static boolean isTripsLine(ArrayList<String> list) {
		int counter = 0;
		if (list.size() >= 7) {
			if (!list.get(0).isEmpty()) {
				counter += 1;
			}
			if (!list.get(2).isEmpty()) {
				counter += 1;
			}
		}
		return counter == 2;
	}

	/**
	 * Checks to make sure the routes line is filled out where it needs to be
	 * @param list the list of inputs in the line
	 * @return true is it matches the expected
	 */
	public static boolean isRoutesLine(ArrayList<String> list) {
		int counter = 0;
		if (list.size() >= 9) {
			if (!list.get(0).isEmpty()) {
				counter += 1;
			}
			if (!list.get(7).isEmpty()) {
				counter += 1;
			}
		}
		return counter == 2;
	}

	/**
	 * Checks to make sure the stop times line is filled out where it needs to be
	 * @param list the list of inputs in the line
	 * @return true is it matches the expected
	 */
	public static boolean isStopTimesLine(ArrayList<String> list) {
		int counter = 0;
		if (list.size() >= 8) {
			if (!list.get(0).isEmpty()) {
				counter += 1;
			}
			if (!list.get(3).isEmpty()) {
				counter += 1;
			}
			if (!list.get(4).isEmpty()) {
				counter += 1;
			}
		}
		return counter == 3;
	}

	/**
	 * Checks to make sure the stops line is filled out where it needs to be
	 * @param list the list of inputs in the line
	 * @return true is it matches the expected
	 */
	public static boolean isStopsLine(ArrayList<String> list) {
		int counter = 0;
		if (list.size() >= 5) {
			if (!list.get(0).isEmpty()) {
				counter += 1;
			}
			if (!list.get(3).isEmpty()) {
				counter += 1;
			}
			if (!list.get(4).isEmpty()) {
				counter += 1;
			}
		}
		return counter == 3;
	}
	///endregion

	/**
	 * helper to make sure all files are loaded in to the program
	 * @return true if they are all loaded in
	 */
	public boolean areFilesLoaded(){
		Collection<String> allFiles = new ArrayList<>(Arrays.asList("stops", "trips", "stopTimes", "routes"));
		return loadedStructures.containsAll(allFiles);
	}

	/**
	 * gets the number of trips that go through a specified stop
	 * @param stop_id the stop that the trips have to go through
	 * @return the number of trips through the stop
	 */
	public String getTripsOnStop(String stop_id){
		if (areFilesLoaded()){
			int i = 0;
			for (GTFSData time : timesList){
				if (time.getValues()[3].equals(stop_id)){
					i++;
				}
			}
			return i + "";
		} else {
			return "Please load in all files first";
		}
	}
}