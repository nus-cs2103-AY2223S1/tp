package seedu.travelr.model.trip;

import java.util.Comparator;

/**
 * Contains the comparators to compare the trips with.
 */
public class TripComparators {
    public static final Comparator<Trip> COMPARE_BY_TITLE = (x, y) -> x.compareTitle(y);
    public static final Comparator<Trip> DO_NOTHING = (x, y) -> 0;
    public static final Comparator<Trip> COMPARE_BY_TIME = (x, y) -> x.compareTime(y);
    public static final Comparator<Trip> COMPARE_BY_LOCATION = (x, y) -> x.compareLocation(y);
    public static final Comparator<Trip> COMPARE_BY_NUM_EVENTS = (x, y) -> x.compareNumberOfEvents(y);
}
