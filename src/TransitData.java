/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 *
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                    Version 2, December 2004
 *
 * Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>
 * Everyone is permitted to copy and distribute verbatim or modified
 * copies of this license document, and changing it is allowed as long
 * as the name is changed.
 *
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 * TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 * 0. You just DO WHAT THE FUCK YOU WANT TO.
 */

import javax.naming.InvalidNameException;
import javax.naming.NameNotFoundException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
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
    private HashMap<Object, GTFSData> gtfsMap;
    private List<GTFSData> gtfsList;
    private final List<String> loadedStructures = new LinkedList<>();
    private final List<Observer> observers = new LinkedList<>();
    //endregion

    //region not Implemented

    /**
     * not Implemented
     *
     * @param observer
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     * not Implemented
     *
     * @param observer
     */
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer ob : observers) {
            ob.notifyObserver(tripsList, timesList, stopsList, routeList);
        }
    }

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
                return isRoutesLine(firstLine);
            case "stop_times.txt":
                return isStopTimesLine(firstLine);
            case "stops.txt":
                return isStopsLine((ArrayList<String>) firstLine);
            case "trips.txt":
                return isTripsLine(firstLine);
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
    public static boolean isTripsLine(List<String> list) {
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
    public static boolean isRoutesLine(List<String> list) {
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
    public static boolean isStopTimesLine(List<String> list) {
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
     *
     * @param objType the type of GTFS data needed
     * @return The corresponding hash map
     */
    public HashMap<Object, GTFSData> getDataMaps(String objType) {
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

    /**
     * This method takes a stop_id of a stop in order to find the next trips leaving from that stop. A currentTime is
     * given, which acts as the lowest time interval value and a timeVarianceMinutes is given which represents how many
     * minutes after the starting time interval should be consider when searching for trips. For example, if the
     * currentTime is 5:30 and the timeVarianceMinutes is 20, then all the trips that are departing between 5:30 and
     * 5:50 will be retrieved and returned as a string.
     *
     * @param stop_id             - the stop_id of the stop used to search for trips
     * @param currentTime         - the lowest time interval
     * @param timeVarianceMinutes - an int representing the minutes after the starting time interval to consider trips
     * @return - a string containing a trip_id and its corresponding departure time for each line
     * @author - Ethan White
     */
    public String GetNextTrips(String stop_id, String currentTime, int timeVarianceMinutes) {
        Calendar cal = Calendar.getInstance();

        Time ending = Time.valueOf(currentTime);

        cal.setTime(ending);
        cal.add(Calendar.MINUTE, timeVarianceMinutes);

        ending.setTime(cal.getTimeInMillis());
        DateTimeFormatter form = DateTimeFormatter.ofPattern("HH:mm:ss");

        String endTime = form.format(ending.toLocalTime());
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (GTFSData stopTime : stopsList) {
            if (stopTime.getValues()[3].equals(stop_id)) {
                if (stopTime.getValues()[2].compareTo(currentTime) > -1 && stopTime.getValues()[2].compareTo(endTime) < 1) {
                    list.add(stopTime.getValues()[2] + " " + stopTime.getValues()[0]);
                }
            }
        }
        Collections.sort(list);
        for (String string : list) {
            sb.append("departure time: ").append(string.substring(0, 8)).append(", trip id:").append(string.substring(8)).append("\n");
        }
        if (sb.toString().equals("")) {
            sb.append("No Matching Trips");
        }
        return sb.toString();
    }

    /* Get the number of routes that go thorough a stop
     *
     * @param stop_id the stop id to search on
     * @return the number of routes that go through the stop
     * @author Ben Fouch
     */
    public String getRoutesThroughStop(String stop_id) {
        List<String> matchingTripIDs = new LinkedList<>();
        List<GTFSData> matchingTrips = new LinkedList<>();
        List<String> matchingRoutes = new LinkedList<>();
        StringBuilder outString = new StringBuilder();

        if (!areFilesLoaded()) {
            return "Please load in all files first";
        }

        for (GTFSData time : timesList) {
            if (time.getValues()[3].equals(stop_id)) {
                matchingTripIDs.add(time.getValues()[0]);
            }
        }

        for (String tripID : matchingTripIDs) {
            matchingTrips.add(trips.get(tripID));
        }

        for (GTFSData trip : matchingTrips) {
            if (!matchingRoutes.contains(trip.getValues()[0])) {
                matchingRoutes.add(trip.getValues()[0]);
                outString.append(trip.getValues()[0]);
                outString.append(", ");
            }
        }

        return outString.length() > 0 ? outString.substring(0, outString.length() - 2) : "No routes found for stop Id: " + stop_id;

    }

    public String getAvSpeed(String trip_id) {
        return "";
    }

    public String getAllTripDistances(){
        if (!areFilesLoaded()) {
            return "Please load in all files first";
        }
        StringBuilder outString = new StringBuilder();
        for (GTFSData trip : tripsList){
            outString.append(getDistanceTrip(trip.getKey()));
        }
        return outString.toString();
    }

    public String getDistanceTrip(String trip_id) {
        GTFSData[] stops = getStopsFromTrip(trip_id);
        String lat1 = stops[0].getValues()[3];
        String lon1 = stops[0].getValues()[4];
        String lat2 = stops[1].getValues()[3];
        String lon2 = stops[1].getValues()[4];
        return HaversineDistance.findDistance(lat1, lat2, lon1, lon2);
    }

    /**
     * Gets the first and last stop on the given trip
     * This implementation does not look for the first in the sequence, because that data line may
     * have been corrupted or missing in the provided files, so gets the first one it can find
     * @param trip_id the trip_id to search on
     * @return a array of the first and last stop
     */
    private GTFSData[] getStopsFromTrip(String trip_id) {
        GTFSData firstStop = null;
        GTFSData lastStop = null;

        for (GTFSData time : timesList) {
            if (time.getValues()[0].equals(trip_id)) {
                if (firstStop == null) {
                    firstStop = time;
                }
                if (lastStop == null) {
                    lastStop = time;
                }
                firstStop = Integer.parseInt(firstStop.getValues()[4]) >
                        Integer.parseInt(time.getValues()[4]) ? time : firstStop;
                lastStop = Integer.parseInt(lastStop.getValues()[4]) <
                        Integer.parseInt(time.getValues()[4]) ? time : lastStop;

            }
        }
        return new GTFSData[]{firstStop, lastStop};
    }

}