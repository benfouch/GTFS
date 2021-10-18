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
    void isStops() {

    }

    // Tests isTrips method with to check different headers
    // to see if it returns the expected results.
    // Test Case 1: Correct header should return true
    // Test Case 2: Completely incorrect header should return false
    // Test Case 3: Partially correct header should return false
    @Test
    void isTrips() {

    }

    // Tests isStopTimes method with to check different headers
    // to see if it returns the expected results.
    // Test Case 1: Correct header should return true
    // Test Case 2: Completely incorrect header should return false
    // Test Case 3: Partially correct header should return false
    @Test
    void isStopTimes() {

    }

    // Tests isRoutes method with to check different headers
    // to see if it returns the expected results.
    // Test Case 1: Correct header should return true
    // Test Case 2: Completely incorrect header should return false
    // Test Case 3: Partially correct header should return false
    @Test
    void isRoutes() {

    }

    // Tests isStopsLine method to check the validity of a line within a stops file
    // Test case 1: Valid line should return true
    // Test case 2: Invalid line should return false
    @Test
    void testIsStopsLine(){
        ArrayList<String> validInput = new ArrayList<String>(Arrays.asList("6712,STATE & 5101 #6712,,43.0444475,-87.9779369".split(",")));
        ArrayList<String> invalidInput = new ArrayList<String>(Arrays.asList("6712,,,43.0444475,".split(",")));

        assertTrue(TransitData.isStopsLine(validInput));
        assertFalse(TransitData.isStopsLine(invalidInput));
    }

    // Tests isStopsTimesLine method to check the validity of a line within a stop_times file
    // Test case 1: Valid line should return true
    // Test case 2: Invalid line should return false
    @Test
    void testIsStopTimesLine(){
        ArrayList<String> validInput = new ArrayList<String>(Arrays.asList("21736564_2535,08:51:00,08:51:00,9113,1,,0,0".split(",")));
        ArrayList<String> invalidInput = new ArrayList<String>(Arrays.asList("21736564_2535,,,9113,,,,".split(",")));

        assertTrue(TransitData.isStopTimesLine(validInput));
        assertFalse(TransitData.isStopTimesLine(invalidInput));
    }

    // Tests isRoutesLine method to check the validity of a line within a routes file
    // Test case 1: Valid line should return true
    // Test case 2: Invalid line should return false
    @Test
    void testIsRoutesLine(){
        ArrayList<String> validInput = new ArrayList<String>(Arrays.asList("12,MCTS,12,Teutonia-Hampton,,3,,008345, ".split(",")));
        ArrayList<String> invalidInput = new ArrayList<String>(Arrays.asList("12,,,,3,,,,".split(",")));

        assertTrue(TransitData.isRoutesLine(validInput));
        assertFalse(TransitData.isRoutesLine(invalidInput));
    }

    // Tests isTripsLine method to check the validity of a line within a trips file
    // Test case 1: Valid line should return true
    // Test case 2: Invalid line should return false
    @Test
    void testIsTripsLine(){
        ArrayList<String> validInput = new ArrayList<String>(Arrays.asList("64,17-SEP_SUN,21736564_2535,60TH-VLIET,0,64102,17-SEP_64_0_23".split(",")));
        ArrayList<String> invalidInput = new ArrayList<String>(Arrays.asList("64,,,60TH-VLIET,,,".split(",")));

        assertTrue(TransitData.isTripsLine(validInput));
        assertFalse(TransitData.isTripsLine(invalidInput));
    }

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
}