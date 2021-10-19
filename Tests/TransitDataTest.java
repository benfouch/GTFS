/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 */



import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TransitDataTest {

    @BeforeEach
    void setUp() {
    }

    // Tests isStops method with to check different headers
    // to see if it returns the expected results.
    // Test Case 1: Correct header should return true
    // Test Case 2: Completely incorrect header should return false
    // Test Case 3: Partially correct header should return false
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
     * @author Ethan White
     *  Tests isStopsLine method to check the validity of a line within a stops file
     *  Test case 1: Valid line should return true
     *  Test case 2: Invalid line should return false
     */
    @Test
    void testIsStopsLine(){
        ArrayList<String> validInput = new ArrayList<String>(Arrays.asList("6712,STATE & 5101 #6712,,43.0444475,-87.9779369".split(",")));
        ArrayList<String> invalidInput = new ArrayList<String>(Arrays.asList("6712,,,43.0444475,".split(",")));

        assertTrue(TransitData.isStopsLine(validInput));
        assertFalse(TransitData.isStopsLine(invalidInput));
    }

    /**
     * @author Ethan White
     *  Tests isStopsTimesLine method to check the validity of a line within a stop_times file
     *  Test case 1: Valid line should return true
     *  Test case 2: Invalid line should return false
     */
    @Test
    void testIsStopTimesLine(){
        ArrayList<String> validInput = new ArrayList<String>(Arrays.asList("21736564_2535,08:51:00,08:51:00,9113,1,,0,0".split(",")));
        ArrayList<String> invalidInput = new ArrayList<String>(Arrays.asList("21736564_2535,,,9113,,,,".split(",")));

        assertTrue(TransitData.isStopTimesLine(validInput));
        assertFalse(TransitData.isStopTimesLine(invalidInput));
    }

    /**
     * @author Ethan White
     *  Tests isRoutesLine method to check the validity of a line within a routes file
     *  Test case 1: Valid line should return true
     *  Test case 2: Invalid line should return false
     */

    @Test
    void testIsRoutesLine(){
        ArrayList<String> validInput = new ArrayList<String>(Arrays.asList("12,MCTS,12,Teutonia-Hampton,,3,,008345, ".split(",")));
        ArrayList<String> invalidInput = new ArrayList<String>(Arrays.asList("12,,,,3,,,,".split(",")));

        assertTrue(TransitData.isRoutesLine(validInput));
        assertFalse(TransitData.isRoutesLine(invalidInput));
    }


    /**
     * @author Ethan White
     * Tests isTripsLine method to check the validity of a line within a trips file
     * Test case 1: Valid line should return true
     * Test case 2: Invalid line should return false
     */
    @Test
    void testIsTripsLine(){
        ArrayList<String> validInput = new ArrayList<String>(Arrays.asList("64,17-SEP_SUN,21736564_2535,60TH-VLIET,0,64102,17-SEP_64_0_23".split(",")));
        ArrayList<String> invalidInput = new ArrayList<String>(Arrays.asList("64,,,60TH-VLIET,,,".split(",")));

        assertTrue(TransitData.isTripsLine(validInput));
        assertFalse(TransitData.isTripsLine(invalidInput));
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
}