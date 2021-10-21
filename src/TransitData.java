/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 */

import DataObjects.*;

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
    private HashMap<Object, GTFSData> routes;
    private List<GTFSData> routeList;
    private HashMap<Object, GTFSData> stops;
    private List<GTFSData> stopsList;
    private HashMap<Object, GTFSData> stopTimes;
    private List<GTFSData> timesList;
    private HashMap<Object, GTFSData> trips;
    private List<GTFSData> tripsList;
    private Stop m_Stop;
    private Controller m_Controller;
    private StopTime m_StopTime;
    private Trip m_Trip;
    private Route m_Route;
    private TransitTable m_TransitTable;
    private Map m_Map;
    private HashMap<Object, GTFSData> gtfsMap;
    private List<GTFSData> gtfsList;
    private List<String> loadedStructures = new LinkedList<>();
    //endregion

    //region not Implemented

    /**
     * not Implemented
     *
     * @param observer
     */
    public void attach(Observer observer) {

    }

    /**
     * not Implemented
     *
     * @param observer
     */
    public void detach(Observer observer) {

    }

    /**
     * not Implemented
     */
    public void notifyObservers() {

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
        List<String> splitLine;
        String[] pathList = path.toString().split("\\\\");
        String fileName = pathList[pathList.length - 1];
        int numSkipped = 0;

        try (Scanner scanner = new Scanner(path)) {
            if (!isValidHeader(fileName, scanner.nextLine())) {
                throw new InvalidNameException("Invalid Header");
            }
            while (scanner.hasNextLine()) {
                splitLine = Arrays.stream(scanner.nextLine().split(",")).collect(Collectors.toList());
                splitLine.add("");
                if (isValidLine(fileName, splitLine)) {
                    newObj = setNewObj(fileName, splitLine);
                    gtfsMap.put(newObj.getKey(), newObj);
                    gtfsList.add(newObj);
                } else {
                    numSkipped++;
                }
            }
        } catch (IOException e) {
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

    private void setDataStructures(String fileName) {
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
        switch (name) {
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
    private boolean isValidLine(String fileName, List<String> firstLine) {
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

    private boolean isValidHeader(String fileName, String firstLine) {
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
     * @param line the line to be checked
     * @return true if the header matches ethe expected
     * @author Ethan White
     * Validates the header for trips
     */
    public static boolean isTrips(String line) {
        return line.equals("route_id,service_id,trip_id,trip_headsign,direction_id,block_id,shape_id");
    }

    /**
     * @param line the line to be checked
     * @return true if the header matches ethe expected
     * @author Ethan White
     * Validates the header for stops
     */
    public static boolean isStops(String line) {
        return line.equals("stop_id,stop_name,stop_desc,stop_lat,stop_lon");
    }

    /**
     * @param line the line to be checked
     * @return true if the header matches ethe expected
     * @author Ethan White
     * Validates the header for stop times
     */
    public static boolean isStopTimes(String line) {
        return line.equals("trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type");
    }

    /**
     * @param line the line to be checked
     * @return true if the header matches ethe expected
     * @author Ethan White
     * Validates the header for routes
     */
    public static boolean isRoutes(String line) {
        return line.equals("route_id,agency_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_color,route_text_color");
    }

    /**
     * @param list the list of inputs in the line
     * @return true is it matches the expected
     * @author Ethan White
     * Checks to make sure the trips line is filled out where it needs to be
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
     * @param list the list of inputs in the line
     * @return true is it matches the expected
     * @author Ethan White
     * Checks to make sure the routes line is filled out where it needs to be
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
     * @param list the list of inputs in the line
     * @return true is it matches the expected
     * @author Ethan White
     * Checks to make sure the stop times line is filled out where it needs to be
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
     * @param list the list of inputs in the line
     * @return true is it matches the expected
     * @author Ethan White
     * Checks to make sure the stops line is filled out where it needs to be
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
     *
     * @return true if they are all loaded in
     */
    public boolean areFilesLoaded() {
        Collection<String> allFiles = new ArrayList<>(Arrays.asList("stops", "trips", "stopTimes", "routes"));
        return loadedStructures.containsAll(allFiles);
    }

    /**
     * gets the number of trips that go through a specified stop
     *
     * @param stop_id the stop that the trips have to go through
     * @return the number of trips through the stop
     */
    public String getTripsOnStop(String stop_id) {
        if (areFilesLoaded()) {
            int i = 0;
            for (GTFSData time : timesList) {
                if (time.getValues()[3].equals(stop_id)) {
                    i++;
                }
            }
            return i + "";
        } else {
            return "Please load in all files first";
        }
    }

    /**
     * Getter for getting any of the maps with the stored data
     * @param objType the type of GTFS data needed
     * @return The corresponding hash map
     */
    public HashMap<Object, GTFSData> getDataMaps(String objType){
        switch (objType) {
            case "routes.txt":
                return routes;
            case "stops.txt":
                return stops;
            case "stop_times.txt":
                return stopTimes;
            case "trips.txt":
                return trips;
        }
        return null;
    }

    public String getRoutesThroughStop(String stop_id){
        List<String> matchingTripIDs = new LinkedList<>();
        List<GTFSData> matchingTrips = new LinkedList<>();
        List<String> matchingRoutes = new LinkedList<>();

        if (areFilesLoaded()) {
            for (GTFSData time : timesList) {
                if (time.getValues()[3].equals(stop_id)) {
                    matchingTripIDs.add(time.getValues()[0]);
                }
            }

            for (GTFSData trip : tripsList){
                if (matchingTripIDs.contains(trip.getValues()[2])){
                    matchingTrips.add(trip);
                }
            }

            for (GTFSData trip: matchingTrips){
                if (!matchingRoutes.contains(trip.getValues()[0])){
                    matchingRoutes.add(trip.getValues()[0]);
                }
            }
            return matchingRoutes.size() + "";
        } else {
            return "Please load in all files first";
        }
    }
}