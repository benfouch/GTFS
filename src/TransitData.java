

/**
 * @author zuberih
 * @version 1.0
 * @created 07-Oct-2021 11:02:27 AM
 */
public class TransitData implements Subject {

	private List<Route> routes;
	private List<Stop> stops;
	private List<StopTime> stopTimes;
	private List<Trip> trips;
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
		return null;
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
		return null;
	}

	/**
	 * 
	 * @param String
	 */
	public void main(args[] String){

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
		return null;
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
		return null;
	}

	/**
	 * 
	 * @param type
	 * @param oldAtribute
	 * @param newAtribute
	 */
	public boolean updateAttributes(Sting type, String oldAtribute, String newAtribute){
		return false;
	}

	/**
	 * 
	 * @param files
	 */
	public boolean uploadFile(file files){
		return false;
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