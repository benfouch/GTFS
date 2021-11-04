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


import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TransitDataTest {

    TransitData TD = new TransitData();

    @BeforeEach
    void setUp() {
        try{
            TD.downloadFiles(Paths.get("resources/routes.txt"));
            TD.downloadFiles(Paths.get("resources/stops.txt"));
            TD.downloadFiles(Paths.get("resources/trips.txt"));
            TD.downloadFiles(Paths.get("resources/stop_times.txt"));
        } catch (Exception e){
            System.out.println("you done messed up");
        }
    }

    /**
     * tests the getRoutesThroughStop method in transit data
     * case 1 : normal and correct input
     * case 2 : A second normal output with a different result
     * case 3 : A fully incorrect input
     * case 4 : A bad, but correctly formatted input (bad because it is not in the files we use for testing)
     */
    @Test
    void testGetRoutesThroughStop(){
       String normalInput = "4340";
       String otherNormalInput = "4338";
       String badInput = "bad";
       String notAStopID = "9999";

       assertEquals("GOL, 30, 30X", TD.getRoutesThroughStop(normalInput));
       assertEquals("30", TD.getRoutesThroughStop(otherNormalInput));
       assertEquals("No routes found for stop Id: bad", TD.getRoutesThroughStop(badInput));
       assertEquals("No routes found for stop Id: 9999", TD.getRoutesThroughStop(notAStopID));
    }

    // Tests isStops method with to check different headers
    // to see if it returns the expected results.
    // Test Case 1: Correct header should return true
    // Test Case 2: Completely incorrect header should return false
    // Test Case 3: Partially correct header should return false
    // Written by Hamza Zuberi
    @Test
    void testIsStops() {
        String case1 = "stop_id,stop_name,stop_desc,stop_lat,stop_lon";
        String case2 = "kjshduasdlkjfsdvkj";
        String case3 = "stop_id,stop_lat,stop_lon";
        assertTrue(TransitData.isStops(case1));
        assertFalse(TransitData.isStops(case2));
        assertFalse(TransitData.isStops(case3));
    }

    // Tests isTrips method with to check different headers
    // to see if it returns the expected results.
    // Test Case 1: Correct header should return true
    // Test Case 2: Completely incorrect header should return false
    // Test Case 3: Partially correct header should return false
    // Written by Hamza Zuberi
    @Test
    void testIsTrips() {
        String case1 = "route_id,service_id,trip_id,trip_headsign,direction_id,block_id,shape_id";
        String case2 = "jkhasjdkladsclkjas";
        String case3 = "route_id,service_id,block_id,shape_id";
        assertTrue(TransitData.isTrips(case1));
        assertFalse(TransitData.isTrips(case2));
        assertFalse(TransitData.isTrips(case3));
    }

    // Tests isStopTimes method with to check different headers
    // to see if it returns the expected results.
    // Test Case 1: Correct header should return true
    // Test Case 2: Completely incorrect header should return false
    // Test Case 3: Partially correct header should return false
    // Written by Ben Fouch
    @Test
    void testIsStopTimes() {
        String case1 = "trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type";
        String case2 = "asdfghj";
        String case3 = "trip_id,arrival_time,departure_time,stop_id";
        assertTrue(TransitData.isStopTimes(case1));
        assertFalse(TransitData.isStopTimes(case2));
        assertFalse(TransitData.isStopTimes(case3));
    }

    // Tests isRoutes method with to check different headers
    // to see if it returns the expected results.
    // Test Case 1: Correct header should return true
    // Test Case 2: Completely incorrect header should return false
    // Test Case 3: Partially correct header should return false
    // Written by Ben Fouch
    @Test
    void testIsRoutes() {
        String case1 = "route_id,agency_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_color,route_text_color";
        String case2 = "asdfghjkl";
        String case3 = "route_long_name,route_desc,route_type,route_url,";
        assertTrue(TransitData.isRoutes(case1));
        assertFalse(TransitData.isRoutes(case2));
        assertFalse(TransitData.isRoutes(case3));
    }

    /**
     *  Tests isStopsLine method to check the validity of a line within a stops file
     */
    @Test
    void testIsStopsLine(){
        // Valid
        String validInput = "6712,STATE & 5101 #6712,,43.0444475,-87.9779369";
        String minimumData = "6712,,,43.0444475,-87.9779369";

        // Invalid
        String invalidInput = "Bad";
        String emptyLine = "";
        String onlyID = "6712,,,,";

        assertTrue(TransitData.isStopTimesLine(split(validInput)));
        assertTrue(TransitData.isStopTimesLine(split(minimumData)));

        assertFalse(TransitData.isStopTimesLine(split(invalidInput)));
        assertFalse(TransitData.isStopTimesLine(split(emptyLine)));
        assertFalse(TransitData.isStopTimesLine(split(onlyID)));
    }

    /**
     * @author Ben Fouch
     *  Tests isStopsTimesLine method to check the validity of a line within a stop_times filee
     */
    @Test
    void testIsStopTimesLine(){
        // Valid
        String validInput = "21736564_2535,08:51:00,08:51:00,9113,1,,0,0";
        String minimumData = "21736564_2535,,,9113,1,,,";
        String noTrailingCommas = "21736564_2535,,,9113,1";

        // Invalid
        String invalidInput = "21736564_2535,,,9113,,,,";
        String emptyLine = "";
        String onlyID = "21736564_2535,,,,,,,";

        assertTrue(TransitData.isStopTimesLine(split(validInput)));
        assertTrue(TransitData.isStopTimesLine(split(minimumData)));
        assertTrue(TransitData.isStopTimesLine(split(noTrailingCommas)));

        assertFalse(TransitData.isStopTimesLine(split(invalidInput)));
        assertFalse(TransitData.isStopTimesLine(split(emptyLine)));
        assertFalse(TransitData.isStopTimesLine(split(onlyID)));
    }

    /**
     *  Tests isRoutesLine method to check the validity of a line within a routes file
     */

    @Test
    void testIsRoutesLine(){
        // Valid
        String validInput = "12,MCTS,12,Teutonia-Hampton,,3,,008345,";
        String minimumData = "12,,,,,,,008345,";
        String noTrailingCommas = "12,,,,,,,008345";

        // Invalid
        String invalidInput = "bad";
        String emptyLine = "";
        String onlyID = "12,,,,,,,,";

        assertTrue(TransitData.isRoutesLine(split(validInput)));
        assertTrue(TransitData.isRoutesLine(split(minimumData)));
        assertTrue(TransitData.isRoutesLine(split(noTrailingCommas)));

        assertFalse(TransitData.isRoutesLine(split(invalidInput)));
        assertFalse(TransitData.isRoutesLine(split(emptyLine)));
        assertFalse(TransitData.isRoutesLine(split(onlyID)));
    }


    /**
     * Tests isTripsLine method to check the validity of a line within a trips file
     * Test case 1: Valid line should return true
     * Test case 2: Invalid line should return false
     */
    @Test
    void testIsTripsLine(){
        // Valid
        String validInput = "64,17-SEP_SUN,21736564_2535,60TH-VLIET,0,64102,17-SEP_64_0_23";
        String minimumData = "64,,21736564_2535,,,,";
        String noTrailingCommas = "64,,21736564_2535";

        // Invalid
        String invalidInput = "bad";
        String emptyLine = "";
        String onlyID = "64,,,,,,";

        assertTrue(TransitData.isTripsLine(split(validInput)));
        assertTrue(TransitData.isTripsLine(split(minimumData)));
        assertTrue(TransitData.isTripsLine(split(noTrailingCommas)));

        assertFalse(TransitData.isTripsLine(split(invalidInput)));
        assertFalse(TransitData.isTripsLine(split(emptyLine)));
        assertFalse(TransitData.isTripsLine(split(onlyID)));
    }

    /**
     * Tests GetNextTrips methods ability to retrieve trips within the time interval for a stop
     * Test case 1: The returned string of trips should be empty if no stop_id is given because no stop could be found
     *              without a stop_id
     * Test case 2: The returned string of trips for a 0 timeVarianceMinutes for stop_id 9113 should contain two stops
     *              that have departure times that are equal to the current time
     * Test case 3: The returned string of trips should be empty if the timeVarianceMinutes is negative because no
     *              departure time would fall between the current time and a time in the past
     * Test case 4: The returned string of trips for stop_id 9113, currentTime as 8:50:00, and timeVarianceMinutes as 10
     *              should contain 6 lines with trips in each in the order they appear from the stop times GTFS document
     * Written by: Ethan White
     */
    @Test
    void testGetNextTrips(){
        String case1 = "";
        String case2 = "21860372_4571, 08:50:00\n21860387_888, 08:50:00\n";
        String case3 = "21736564_2535, 08:51:00\n21736573_551, 08:51:00\n21849820_1011, 08:57:00\n21858756_642, " +
                "08:59:00\n21860372_4571, 08:50:00\n21860387_888, 08:50:00\n";
        assertEquals(case1, TD.GetNextTrips("","08:50:00", 10));
        assertEquals(case2, TD.GetNextTrips("9113","08:50:00", 0));
        assertEquals(case1, TD.GetNextTrips("9113","08:50:00", -1));
        assertEquals(case3, TD.GetNextTrips("9113","08:50:00", 10));

    }
    //region not implemented
    @Test
    void testFinalize() {
    }

    @Test
    void exportFiles() {
    }

    @Test
    void getAverageSpeed() {
    }

    @Test
    void getNumTrips() {
    }

    @Test
    void getTripDistance() {
    }

    @Test
    void plotBus() {
    }

    @Test
    void plotMap() {
    }

    @Test
    void searchRoute() {
    }

    @Test
    void searchRouteForTrips() {
    }

    @Test
    void searchStop() {
    }

    @Test
    void searchStopTrip() {
    }

    @Test
    void updateAttributes() {
    }

    @Test
    void uploadFiles() {
    }
    //endregion

    private List<String> split(String line){
        List<String> returnVar = new ArrayList<>(Arrays.asList(line.split(",")));
        returnVar.add("");
        return returnVar;
    }
}