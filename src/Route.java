/*
 * Course: CS2030
 * Fall 2021
 * Lab 5
 * Name: Team F
 * Created: 07-Oct-2021
 */


import java.util.List;

/**
 * An Object to make data management easier
 *
 * @author Ben Fouch
 * @version 1.0
 * @created 07-Oct-2021 11:02:19 AM
 */
public class Route implements GTFSData {

    private final String agency_id;
    private final String route_color;
    private final String route_desc;
    private final String route_id;
    private final String route_long_name;
    private final String route_short_name;
    private final String route_text_color;
    private final String route_type;
    private final String route_url;

    public Route(List<String> params) {
        int i = 0;
        this.route_id = params.get(i++);
        this.agency_id = params.get(i++);
        this.route_short_name = params.get(i++);
        this.route_long_name = params.get(i++);
        this.route_desc = params.get(i++);
        this.route_type = params.get(i++);
        this.route_text_color = params.get(i++);
        this.route_color = params.get(i++);
        this.route_url = params.get(i);
    }

    /**
     * A better toString for the data dumb for lab 5
     *
     * @return a text representation of the data in the route object
     */
    @Override
    public String toString() {
        return route_id + "," +
               agency_id + "," +
               route_short_name + "," +
               route_long_name + "," +
               route_desc + "," +
               route_type + "," +
               route_text_color + "," +
               route_color + "," +
               route_url;
    }

    @Override
    public String getHeader() {
        return "route_id,agency_id,route_short_name,route_long_name,route_desc," +
                "route_type,route_url,route_color,route_text_color\n";
    }

    @Override
    public String getKey() {
        return String.valueOf(route_id);
    }

	@Override
	public String[] getValues() {
		return toString().split(",");
	}
}