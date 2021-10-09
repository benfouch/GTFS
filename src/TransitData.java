
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.nio.file.Path;
import java.util.*;

/**
 * @author Ben Fouch
 * @version 1.0
 * @created 07-Oct-2021 11:02:27 AM
 * I will be
 */
public class TransitData implements Subject {

	public HashMap<String, Route> routes;
	public HashMap<Integer , Stop> stops;
	public HashMap<Integer, StopTime> stopTimes;
	public HashMap<String, Trip> trips;
	public Stop m_Stop;
	public Controller m_Controller;
	public StopTime m_StopTime;
	public Trip m_Trip;
	public Route m_Route;
	public TransitTable m_TransitTable;
	public Map m_Map;

	public TransitData(){

	}

	public void finalize() throws Throwable {

	}

	public boolean exportFiles(){
		return false;
	}

	/**
	 * 
	 * @param trip_id
	 */
	public float getAverageSpeed(float trip_id){
		return 0;
	}

	/**
	 * 
	 * @param stop_id
	 */
	public int getNumTrips(int stop_id){
		return 0;
	}

	/**
	 * 
	 * @param trip_id
	 */
	public float getTripDistance(float trip_id){
		return 0;
	}

	/**
	 * 
	 * @param route_id
	 */
	public boolean plotBus(String route_id){
		return false;
	}

	public boolean plotMap(){
		return false;
	}

	/**
	 * 
	 * @param route_id
	 */
	public int searchRoute(String route_id){
		return 0;
	}

	/**
	 * 
	 * @param route_id
	 */
	public float searchRouteForTrips(String route_id){
		return 0;
	}

	/**
	 * 
	 * @param stop_id
	 */
	public String searchStop(int stop_id){
		return "";
	}

	/**
	 * 
	 * @param stop_id
	 */
	public float searchStopTrip(int stop_id){
		return 0;
	}

	/**
	 * 
	 * @param type
	 * @param oldAtribute
	 * @param newAtribute
	 */
	public boolean updateAttributes(String type, String oldAtribute, String newAtribute){
		return false;
	}

	/**
	 * 
	 * @param path
	 */
	public boolean uploadFiles(Path path){
		String[] pathList = path.toString().split("\\\\");
		String fileName = pathList[pathList.length-1];
		boolean returnValue = false;
		switch (fileName){
			case "routes.txt":
				returnValue = saveRoutes(path);
				break;
			case "stop_time.txt":
				returnValue = saveStopTimes(path);
				break;
			case "stops.txt":
				returnValue = saveStops(path);
				break;
			case "trips.txt":
				returnValue = saveTrips(path);
				break;
		}

		return returnValue;
	}

	private boolean saveRoutes(Path path){
		routes = new HashMap<String, Route>();
		boolean firstLine = true;
		String line;
		String[] splitLine;
		Route newRoute;
		int count = 0;
		try (Scanner scanner = new Scanner(path)){
			while (scanner.hasNextLine()){
				line = scanner.nextLine();
				if (!firstLine){
					splitLine = line.split(",");
					newRoute = new Route(splitLine[count++], splitLine[count++], splitLine[count++],
							splitLine[count++],splitLine[count++], splitLine[count++],
							splitLine[count++], splitLine[count++], splitLine.length >= 9 ? splitLine[count] : "");
					count = 0;
					routes.put(newRoute.route_id, newRoute);
				}
				firstLine = false;
			}

		} catch (Exception e){
			System.out.println("Error in <TransitData.saveRoutes()>");
			return false;
		}
		return true;
	}

	private boolean saveStopTimes(Path path){
		stopTimes = new HashMap<Integer, StopTime>();
		boolean firstLine = true;
		String line;
		String[] splitLine;
		StopTime newStopTime;
		int count = 0;
		try (Scanner scanner = new Scanner(path)){
			while (scanner.hasNextLine()){
				line = scanner.nextLine();
				if (!firstLine){
					splitLine = line.split(",");
					newStopTime = new StopTime(splitLine[count++], splitLine[count++], splitLine[count++],
							splitLine[count++],splitLine[count++], splitLine[count++],
							splitLine[count++], splitLine.length >= 8 ? splitLine[count] : "");
					count = 0;
					stopTimes.put(newStopTime.stop_id, newStopTime);
				}
				firstLine = false;
			}

		} catch (Exception e){
			System.out.println("Error in <TransitData.saveRoutes()>");
			return false;
		}
		return true;
	}

	private boolean saveStops(Path path){
		stops = new HashMap<Integer, Stop>();
		boolean firstLine = true;
		String line;
		String[] splitLine;
		Stop newStop;
		int count = 0;
		try (Scanner scanner = new Scanner(path)){
			while (scanner.hasNextLine()){
				line = scanner.nextLine();
				if (!firstLine){
					splitLine = line.split(",");
					newStop = new Stop(splitLine[count++], splitLine[count++], splitLine[count++],
							splitLine[count++], splitLine.length >= 5 ? splitLine[count] : "");
					count = 0;
					stops.put(newStop.stop_id, newStop);
				}
				firstLine = false;
			}

		} catch (Exception e){
			System.out.println("Error in <TransitData.saveRoutes()>");
			return false;
		}
		return true;
	}

	private boolean saveTrips(Path path){
		trips = new HashMap<String, Trip>();
		boolean firstLine = true;
		String line;
		String[] splitLine;
		Trip newTrip;
		int count = 0;
		try (Scanner scanner = new Scanner(path)){
			while (scanner.hasNextLine()){
				line = scanner.nextLine();
				if (!firstLine){
					splitLine = line.split(",");
					newTrip = new Trip(splitLine[count++], splitLine[count++], splitLine[count++],
							splitLine[count++],splitLine[count++], splitLine[count++],
							splitLine.length >= 7 ? splitLine[count] : "");
					count = 0;
					trips.put(newTrip.route_id, newTrip);
				}
				firstLine = false;
			}

		} catch (Exception e){
			System.out.println("Error in <TransitData.saveRoutes()>");
			return false;
		}
		return true;
	}

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

}