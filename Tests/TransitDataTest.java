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
}