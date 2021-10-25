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

    public String agency_id;
    public String route_color;
    public String route_desc;
    public String route_id;
    public String route_long_name;
    public String route_short_name;
    public String route_text_color;
    public int route_type;
    public String route_url;

    public Route(List<String> params) {
        int i = 0;
        this.route_id = params.get(i++);
        this.agency_id = params.get(i++);
        this.route_short_name = params.get(i++);
        this.route_long_name = params.get(i++);
        this.route_desc = params.get(i++);
        this.route_type = params.get(i).equals("") ? -1 : Integer.parseInt(params.get(i++));
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
		return new String[]{route_id, agency_id, route_short_name, route_long_name, route_desc,
                String.valueOf(route_type), route_text_color, route_color, route_url};
	}
}