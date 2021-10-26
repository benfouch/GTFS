/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
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

       assertEquals("3", TD.getRoutesThroughStop(normalInput));
       assertEquals("1", TD.getRoutesThroughStop(otherNormalInput));
       assertEquals("0", TD.getRoutesThroughStop(badInput));
       assertEquals("0", TD.getRoutesThroughStop(notAStopID));
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