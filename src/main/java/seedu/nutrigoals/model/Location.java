package seedu.nutrigoals.model;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.AppUtil.checkArgument;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 */
public class Location {
    // REGEX credit:
    // https://stackoverflow.com/questions/3518504/regular-expression-for-matching-latitude-longitude-coordinates
    public static final String VALIDATION_REGEX =
        "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$";
    public static final String MESSAGE_CONSTRAINTS =
        "Latitude and Longitude must take on a value in range [-90, 90] and [-180, 180] respectively\n"
            + "Values are separated by a comma";
    private static Map<String, Location> nameToLocMap = Collections.unmodifiableMap(createNameToLocMap());
    public final String name;
    public final double latitude;
    public final double longitude;


    private static Map<String, Location> createNameToLocMap() {
        Map<String, Location> out = new HashMap<>();
        out.put("COM2", new Location("COM2", "1.2942815638814327, 103.77410024788284"));
        out.put("S17", new Location("S17", "1.2976996370988612, 103.78060787462833"));
        out.put("CLB", new Location("CLB", "1.296642317024345, 103.77322870790687"));
    }
    /**
     * @param value Latitude and Longitude
     */
    public Location(String name, String value) {
        requireNonNull(name);
        requireNonNull(value);
        checkArgument(isValidLocation(value), MESSAGE_CONSTRAINTS);
        this.name = name;
        latitude = Double.parseDouble(value.split(", ")[0]);
        longitude = Double.parseDouble(value.split(", ")[1]);
    }

    /**
     * @param other
     * @return
     */
    public Double distTo(Location other) {
        var radius = 6371; // Radius of the earth in km
        var dLat = deg2rad(other.latitude - this.latitude);
        var dLon = deg2rad(other.longitude - this.longitude);
        var a =
            Math.sin(dLat / 2.) * Math.sin(dLat / 2.0)
                + Math.cos(deg2rad(this.latitude)) * Math.cos(deg2rad(other.latitude))
                * Math.sin(dLon / 2.) * Math.sin(dLon / 2.0);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var d = radius * c; // Distance in km
        return d;
    }
    private static double deg2rad(double degree) {
        return degree * (Math.PI / 180);
    }

    /**
     * @param name Name of location
     * @return boolean whether the location exists in database
     */
    public static boolean isValidLocationName(String name) {
        requireNonNull(name);
        return nameToLocMap.containsKey(name);
    }

    /**
     * @param latAndLong
     * @return
     */
    public static boolean isValidLocation(String latAndLong) {
        return Optional.of(latAndLong)
            .map(v -> v.matches(VALIDATION_REGEX))
            .orElse(false);
    }
    @Override
    public String toString() {
        return name;
    }
    @Override
    public boolean equals(Object other) {
        if (other instanceof Location) {
            return this.latitude == ((Location) other).latitude
                && this.longitude == ((Location) other).longitude;
        }
        return false;
    }
}
