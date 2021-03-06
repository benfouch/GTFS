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
 *
 * @author Ben Fouch
 * @version 1.0
 * @created 07-Oct-2021 11:02:32 AM
 */
public class Trip implements GTFSData {

    private final String block_id;
    private final String direction_id;
    private final String route_id;
    private final String service_id;
    private final String shape_id;
    private final String trip_headsign;
    private final String trip_id;

    public Trip(List<String> params) {
        int i = 0;
        this.route_id = params.get(i++);
        this.service_id = params.get(i++);
        this.trip_id = params.get(i++);
        this.trip_headsign = params.get(i++);
        this.direction_id = params.get(i++);
        this.block_id = params.get(i++);
        this.shape_id = params.get(i);
    }

    /**
     * A better toString for the data dumb for lab 5
     *
     * @return a text representation of the data in the Trip object
     */
    public String toString() {
        return route_id + "," +
                service_id + "," +
                trip_id + "," +
                trip_headsign + "," +
                direction_id + "," +
                block_id + "," +
                shape_id;
    }

    @Override
    public String getHeader() {
        return "route_id,service_id,trip_id,trip_headsign," +
                "direction_id,block_id,shape_id\n";
    }

    @Override
    public String getKey() {
        return String.valueOf(trip_id);
    }

    @Override
    public String[] getValues() {
        return toString().split(",");
    }

}