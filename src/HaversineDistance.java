/**
 * THIS FILE IS FROM https://gist.github.com/vananth22/888ed9a22105670e7a4092bdcf0d72e4
 */

/**
 * This is the implementation Haversine Distance Algorithm between two places
 * @author ananth
 * R = earth’s radius (mean radius = 6,371km)
Δlat = lat2− lat1
Δlong = long2− long1
a = sin²(Δlat/2) + cos(lat1).cos(lat2).sin²(Δlong/2)
c = 2.atan2(√a, √(1−a))
d = R.c
 *
 */

public class HaversineDistance {

    /**
     * arg 1- latitude 1
     * arg 2 — latitude 2
     * arg 3 — longitude 1
     * arg 4 — longitude 2
     * This method has been adapted for use in this application
     * CHANGES MADE:
     *  - the radius is now miles
     *  - changed method signature
     *  - added a return type of the distance as a string
     */
    public static String findDistance(String latI, String lonI, String latII, String lonII) {
        // TODO Auto-generated method stub
        final int R = 3955; // Radius of the earth //CHANGED FROM ORIGINAL
        Double lat1 = Double.parseDouble(latI);
        Double lon1 = Double.parseDouble(lonI);
        Double lat2 = Double.parseDouble(latII);
        Double lon2 = Double.parseDouble(lonII);
        double latDistance = toRad(lat2-lat1);
        double lonDistance = toRad(lon2-lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = R * c;

        return distance + "";

    }

    /**
     * This method is also made by Vananth22 on GitHib
     * @param value
     * @return
     */
    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }

}