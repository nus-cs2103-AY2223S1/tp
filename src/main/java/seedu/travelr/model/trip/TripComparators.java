package seedu.travelr.model.trip;

import java.util.Comparator;

/**
 * Contains the comparators to compare the trips with.
 */
public class TripComparators {
    public static final Comparator<Trip> compareByTitle = (x, y) -> x.compareTitle(y);
}
