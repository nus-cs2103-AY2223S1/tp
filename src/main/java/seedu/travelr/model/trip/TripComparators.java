package seedu.travelr.model.trip;

import java.util.Comparator;

/**
 * Contains the comparators to compare the trips with.
 */
public class TripComparators {
    public static final Comparator<Trip> COMPARE_BY_TITLE = (x, y) -> x.compareTitle(y);
}
