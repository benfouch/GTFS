

/**
 * @author Ben Fouch
 * @version 1.0
 * @created 07-Oct-2021 11:02:19 AM
 */
public class Route {

	public String agency_id;
	public String route_color;
	public String route_desc;
	public String route_id;
	public String route_long_name;
	public String route_short_name;
	public String route_text_color;
	public int route_type;
	public String route_url;

	public Route(String route_id, String agency_id,String route_short_name,String route_long_name,
				 String route_desc,String route_type,String route_url,String route_color,
				 String route_text_color){
		this.route_id = route_id;
		this.agency_id = agency_id;
		this.route_short_name = route_short_name;
		this.route_long_name = route_long_name;
		this.route_desc = route_desc;
		this.route_type = Integer.parseInt(route_type);
		this.route_text_color = route_text_color;
		this.route_color = route_color;
		this.route_url = route_url;
	}

	public String toString(){
		return "Route_id: " + route_id + "\n" +
				"\tAgency_id: " + agency_id + "\n" +
				"\tRoute_short_name: " + route_short_name + "\n" +
				"\tRoute_long_name: " + route_long_name + "\n" +
				"\tRoute_desc: " + route_desc + "\n" +
				"\tRoute_type: " + route_type + "\n" +
				"\tRoute_text_color: " + route_text_color + "\n" +
				"\tRoute_color: " + route_color + "\n" +
				"\tRoute_url: " + route_url + "\n";
	}

}