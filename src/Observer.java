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

import java.util.HashMap;
import java.util.List;

/**
 * @author zuberih
 * @version 1.0
 * @created 07-Oct-2021 11:02:17 AM
 */
public interface Observer {
    void notifyObserver(List<GTFSData> trips,List<GTFSData> stopTimes,
                        List<GTFSData> stops, List<GTFSData> routes);
}