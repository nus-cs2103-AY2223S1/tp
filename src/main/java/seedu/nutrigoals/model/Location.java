package seedu.nutrigoals.model;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.AppUtil.checkArgument;

import java.util.Optional;

/**
 * Represents a Location object
 */
public class Location {
    // REGEX credit:
    // https://stackoverflow.com/questions/3518504/regular-expression-for-matching-latitude-longitude-coordinates
    public static final String VALIDATION_REGEX =
        "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$";
    public static final String MESSAGE_CONSTRAINTS =
        "Latitude and Longitude must take on a value in range [-90, 90] and [-180, 180] respectively\n"
            + "Values are separated by a comma";
    public final String name;
    public final double latitude;
    public final double longitude;

    Location() { // just for the purpose of serializing
        this.name = "";
        this.latitude = 0;
        this.longitude = 0;
    }

    /**
     * @param value Latitude and Longitude
     */
    // Right now this is for developer's use and not for the user. so the parsing is not a big issue
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

    private static boolean isValidLocation(String latAndLong) {
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
