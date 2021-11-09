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


import java.util.List;

/**
 * An Object to make data management easier
 * @author Ben Fouch
 * @version 1.0
 * @created 07-Oct-2021 11:02:21 AM
 */
public class Stop implements GTFSData{

	private final String stop_desc;
	private final String stop_id;
	private final String stop_lat;
	private final String stop_lon;
	private final String stop_name;

	public Stop(List<String> params){
		int i = 0;
		this.stop_id = params.get(i++);
		this.stop_name = params.get(i++);
		this.stop_desc = params.get(i++);
		this.stop_lat = params.get(i++);
		this.stop_lon = params.get(i);
	}

	/**
	 * A better toString for the data dumb for lab 5
	 * @return a text representation of the data in the Stop object
	 */
	public String toString(){
		return stop_id + "," +
				stop_name + "," +
				stop_desc + "," +
				stop_lat + "," +
				stop_lon;
	}

	@Override
	public String getHeader() {
		return "stop_id,stop_name,stop_desc,stop_lat,stop_lon\n";
	}

	@Override
	public String getKey() {
		return String.valueOf(stop_id);
	}

	@Override
	public String[] getValues() {
		return toString().split(",");
	}
}